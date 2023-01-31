package edu.spring.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MatchController {
	@GetMapping("")
	@ResponseBody
	public String helloAction() {
		return "Hello world !";
	}
	
	@GetMapping(path={"msg/{content}","msg/{content}/"})
	@ResponseBody
	public String messageAction(@PathVariable("content") String cont) {
		return cont;
	}

	@GetMapping(path={"msg/view/{content}","msg/view/{content}/"})
	public String messageViewAction(ModelMap data, @PathVariable("content") String content) {
		int value=220;
		data.addAttribute("value",value);
		return "helloview";
	}
	public ModelAndView messageViewAction2(@PathVariable("content") String cont) {
		ModelAndView mv = new ModelAndView("helloview");
		mv.addObject("value",220);
		mv.addObject("html","<h2>Test de HTML</h2>");
		return mv;
		
	}
}
