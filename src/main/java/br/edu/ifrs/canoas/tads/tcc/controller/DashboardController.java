package br.edu.ifrs.canoas.tads.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by elvis on 03/14/18.
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@GetMapping("/index")
	public ModelAndView greetings() {
		return new ModelAndView("/dashboard/index");
		
	}
	
		

}