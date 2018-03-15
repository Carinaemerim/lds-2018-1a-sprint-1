package br.edu.ifrs.canoas.tads.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cassiano on 3/11/18.
 */
@Controller
@RequestMapping("/evaluation")
public class EvaluationController {

	@GetMapping("/list")
	public ModelAndView greetings() {
		return new ModelAndView("/evaluation/list");
	}

	@GetMapping("/theme")
	public ModelAndView theme() {
		return new ModelAndView("/evaluation/theme");
	}

	@GetMapping("/proposal")
	public ModelAndView proposal() {
		return new ModelAndView("/evaluation/proposal");
	}

	@GetMapping("/termpaper")
	public ModelAndView termpaper() {
		return new ModelAndView("/evaluation/termpaper");
	}
}
