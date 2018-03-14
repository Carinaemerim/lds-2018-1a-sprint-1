package br.edu.ifrs.canoas.tads.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/schedule")
@Controller
public class ScheduleController {

	@GetMapping("/index")
	public ModelAndView proposal() {
		return new ModelAndView("/schedule/index");
	}
}
