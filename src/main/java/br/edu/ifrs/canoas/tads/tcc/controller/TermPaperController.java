package br.edu.ifrs.canoas.tads.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/paper")
@Controller
public class TermPaperController {

	@GetMapping("/proposal")
	public ModelAndView proposal() {
		return new ModelAndView("/term-paper/proposal");
	}
}
