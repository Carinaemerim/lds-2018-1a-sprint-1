package br.edu.ifrs.canoas.tads.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by rodrigo on 2/21/17.
 */
@Controller
public class HomeController {

	@GetMapping("/")
	public ModelAndView greetings() {
		return new ModelAndView("/index");
	}

}
