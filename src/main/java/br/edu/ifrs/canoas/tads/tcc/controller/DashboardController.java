package br.edu.ifrs.canoas.tads.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrs.canoas.tads.tcc.service.ScheduleService;

/**
 * Created by elvis on 03/14/18.
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	private final ScheduleService scheduleService;
	
	public DashboardController(ScheduleService scheduleService) {
		super();
		this.scheduleService = scheduleService;
	}



	@GetMapping("/index")
	public ModelAndView greetings() {//Long id
		ModelAndView mav = new ModelAndView("/dashboard/index");
		
		mav.addObject("tasks",scheduleService.listAll());
		//mav.addObject("status",scheduleService.getTaskStatus(id));
		return mav;
		
		
	}
	

}