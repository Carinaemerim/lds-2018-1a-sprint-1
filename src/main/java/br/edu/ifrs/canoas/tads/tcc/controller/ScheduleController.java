package br.edu.ifrs.canoas.tads.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrs.canoas.tads.tcc.service.ScheduleService;

@RequestMapping("/schedule")
@Controller
public class ScheduleController {
	
	private final ScheduleService scheduleService;

	public ScheduleController(ScheduleService scheduleService) {
		super();
		this.scheduleService = scheduleService;
	}

	@GetMapping("/index")
    @RequestMapping(method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("/schedule/index");
	}
	
	@GetMapping("/edit")
	public ModelAndView edit() {
		return new ModelAndView("/schedule/edit");
	}

}
