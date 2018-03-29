package br.edu.ifrs.canoas.tads.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrs.canoas.tads.tcc.service.UserService;
import lombok.Data;

@RequestMapping("/assessment-bank")
@Controller
@Data
public class AssessmentBankController {
	private final UserService userService;

	@GetMapping("/list")
	public ModelAndView assessmentBank() {		
		return new ModelAndView("/assessment-bank/list");
	}
	
	@GetMapping("/listprofessors")
	public ModelAndView selectassessmentBank() {		
		ModelAndView mav = new ModelAndView("/assessment-bank/listprofessors");
		mav.addObject("professors", userService.getAdvisors());
		return mav;
	}	
	
	
	
}