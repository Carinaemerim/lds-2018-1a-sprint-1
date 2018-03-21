package br.edu.ifrs.canoas.tads.tcc.controller;

import br.edu.ifrs.canoas.tads.tcc.domain.DocumentType;
import br.edu.ifrs.canoas.tads.tcc.service.EvaluationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cassiano on 3/11/18.
 */
@Controller
@AllArgsConstructor
@RequestMapping("/evaluation")
public class EvaluationController {

	private final EvaluationService evaluationService;

	@GetMapping("/list")
	public ModelAndView greetings(/*@RequestParam("academicyear") String academicYear*/) {
		ModelAndView mav = new ModelAndView("/evaluation/list");

		return mav;
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
