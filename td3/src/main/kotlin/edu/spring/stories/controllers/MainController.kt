package edu.spring.stories.controllers

import edu.spring.stories.entities.Developer
import edu.spring.stories.entities.Story
import edu.spring.stories.repositories.DeveloperRepository
import edu.spring.stories.repositories.StoryRepository
import org.springframework.stereotype.Controller
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
    lateinit var developerRepository: DeveloperRepository
    lateinit var storyRepository: StoryRepository

    @RequestMapping(path=["/",""])
    fun index(): String {
        return "index"
    }
    @PostMapping("/developer/add")
    fun addDeveloper(@RequestParam developer: Developer): RedirectView {
        developerRepository.save(developer)
        return RedirectView("/")
    }

    @PostMapping("/developer/{id}/story")
    fun addStory(@PathVariable id:Int, @RequestParam story: Story): RedirectView {
        developerRepository.findById(id).ifPresent {
            it.addStory(story)
            developerRepository.save(it)
        }
        return RedirectView("/")
    }

    @GetMapping("/story/{id}/giveup")
    @ResponseBody
    fun giveUpStory(@PathVariable id:String): RedirectView {
        var dev: List<Developer> =developerRepository.findByStoriesName(id!!)

        return RedirectView("/")
    }
    @GetMapping("/developer/{id}/delete")
    fun deleteDeveloper(@PathVariable id:Int?): RedirectView {
        developerRepository.findById(id!!).ifPresent { developerRepository.delete(it) }
        return RedirectView("/")
    }
    @PostMapping("/story/{id}/action")
    fun addAction(@PathVariable id:Int?): RedirectView {
        return RedirectView("/")
    }
}