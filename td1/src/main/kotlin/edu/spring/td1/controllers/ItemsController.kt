package edu.spring.td1.controllers

import edu.spring.td1.models.Item
import edu.spring.td1.services.UIMessage
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@Controller
@SessionAttributes("items", "message")

class ItemsController{
    @get:ModelAttribute("items")
    val items: Set<Item>
        get(){
            var elements= HashSet<Item>()
            elements.add(Item("Foo"))
            return elements
        }

    @get:ModelAttribute("message")
    val message: UIMessage.Message
        get(){
            return UIMessage.message("Bienvenue", "Bienvenue dans tes morts")
        }

    @RequestMapping("/")
    fun indexAction(@RequestAttribute msg:UIMessage.Message?):String{
        return "index"
    }

    @PostMapping("/addNew")
    fun addNew(@ModelAttribute("nom") item:Item, @SessionAttribute("message") msg: UIMessage.Message, @SessionAttribute("items") items:HashSet<Item>, attrs:RedirectAttributes): RedirectView {
        if(items.add(item)) attrs.addFlashAttribute("msg", UIMessage.message("Ajout","${item.nom} ajouté dans les items"))
        else attrs.addFlashAttribute("msg",UIMessage.message("Ajout", "${item.nom} est déjà dans les items!","error","warning circle"))
        return RedirectView("/")
    }

    @GetMapping("/new")
    fun newAction():String{
        return "newForm"
    }
    @GetMapping("/del")
    fun delAction():String{
        return "deleteForm"
    }
}