package edu.spring.stories.controllers

import ch.qos.logback.core.net.SyslogOutputStream
import edu.spring.stories.entities.Developer
import edu.spring.stories.entities.Story
import edu.spring.stories.repositories.DeveloperRepository
import edu.spring.stories.repositories.StoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/")
class MainController {
    @Autowired
    lateinit var developerRepository: DeveloperRepository
    @Autowired
    lateinit var storyRepository: StoryRepository

    @RequestMapping(path=["/",""])
    fun index(model: MutableMap<String, Any>): String {
        model["developers"] = developerRepository.findAll()
        model["nullstories"] = storyRepository.findByDeveloperIsNull()
        model["devcount"] = developerRepository.count()
        model["storycount"] = storyRepository.findByDeveloperIsNull().size
        model["devempty"] = developerRepository.count()==0L
        model["storyempty"] = storyRepository.findByDeveloperIsNull().isEmpty()
        return "index"
    }
    @PostMapping("/developer/add")
    fun addDeveloper(@RequestParam firstname: String, lastname: String): RedirectView {
        developerRepository.save(Developer(firstname, lastname))
        return RedirectView("/")
    }

    @PostMapping("/developer/{id}/story")
    fun addStory(@PathVariable id:Int, @RequestParam story: String): RedirectView {
        developerRepository.findById(id).ifPresent {
            var sto =Story(story)

            System.out.println("${sto.id} ${sto.developer} ${sto.name}")
            System.out.println("${it.firstname} ${it.lastname} ${it.id}")
            System.out.println("${it.stories.count()}")
            it.addStory(sto)
            developerRepository.save(it)

            System.out.println("${it.stories.count()}")
            System.out.println("story added")
            it.stories.forEach({System.out.println("${it.id} ${it.developer} ${it.name}")})
        }

        return RedirectView("/")
    }

    @GetMapping("/story/{id}/giveup")
    @ResponseBody
    fun giveUpStory(@PathVariable id:String,@RequestParam name:String): RedirectView {
        storyRepository.findById(Integer.parseInt(id)).ifPresent {
            it.developer?.giveUpStory(it)
            it.developer = null
            storyRepository.save(it)
        }
        return RedirectView("/")
    }
    @GetMapping("/developer/{id}/delete")
    fun deleteDeveloper(@PathVariable id:Int?): RedirectView {
        developerRepository.findById(id!!).ifPresent {
            it.preRemove()
            developerRepository.delete(it)
        }
        return RedirectView("/")
    }
    @PostMapping("/story/{id}/action" )
    fun addAction(@PathVariable id:Int?,@RequestParam action:String, @RequestParam dev:String): RedirectView {
        if(action=="remove"){
            storyRepository.findById(id!!).ifPresent {
                storyRepository.delete(it)
            }
        }
        else if(action=="affect"){
            var devid=Integer.parseInt(dev)
            developerRepository.findById(devid).ifPresent {
                var deve=it
                storyRepository.findById(id!!).ifPresent {
                    deve.addStory(it)
                    developerRepository.save(deve)
                }

            }
        }
        return RedirectView("/")
    }
}