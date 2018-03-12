package br.edu.ifrs.canoas.tads.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/assessment-bank")
@Controller
public class AssessmentBankController {

	@GetMapping("/list")
	public ModelAndView assessmentBank() {		
		return new ModelAndView("/assessment-bank/list");
	}
}