package br.edu.ifrs.canoas.tads.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/notification")
public class NotificationController {

	@GetMapping("/notification")
	public ModelAndView Notification() {

		return new ModelAndView("/notification/notification");
	}

}
