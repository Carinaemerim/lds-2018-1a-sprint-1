package br.edu.ifrs.canoas.tads.tcc.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrs.canoas.tads.tcc.config.auth.UserImpl;
import br.edu.ifrs.canoas.tads.tcc.domain.Advice;
import br.edu.ifrs.canoas.tads.tcc.domain.Document;
import br.edu.ifrs.canoas.tads.tcc.domain.Evaluation;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.repository.UserRepository;
import br.edu.ifrs.canoas.tads.tcc.service.EvaluationService;
import br.edu.ifrs.canoas.tads.tcc.service.ScheduleService;
import br.edu.ifrs.canoas.tads.tcc.service.TermPaperService;
import br.edu.ifrs.canoas.tads.tcc.service.UserService;

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
	public ModelAndView greetings(@AuthenticationPrincipal UserImpl activeUser) {//Long id
		ModelAndView mav = new ModelAndView("/dashboard/index");
		mav.addObject("currPeriod", scheduleService.getPeriod());
		mav.addObject("tasks",scheduleService.listAll());
		return mav;
	
	}
		

}