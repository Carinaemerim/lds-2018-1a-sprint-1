package br.edu.ifrs.canoas.tads.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/document")
@Controller
public class DocumentController {

	@GetMapping("/document")
	public ModelAndView document() {

		return new ModelAndView("/document/document");
	}
}
