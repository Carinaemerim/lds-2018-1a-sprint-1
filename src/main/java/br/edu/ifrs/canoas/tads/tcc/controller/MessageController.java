package br.edu.ifrs.canoas.tads.tcc.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrs.canoas.tads.tcc.config.auth.UserImpl;
import br.edu.ifrs.canoas.tads.tcc.service.MessageService;
import lombok.AllArgsConstructor;

@RequestMapping("/chat")
@Controller
@AllArgsConstructor
public class MessageController {
	private final MessageService messageService;
	
	@GetMapping("/list")
	public ModelAndView listChats(@AuthenticationPrincipal UserImpl activeUser) {
		ModelAndView mav = new ModelAndView("/document/document");
		mav.addObject("messages", messageService.findAllBySenderOrByReceiverOrderByDate(activeUser.getUser()));
		return mav;
	}
}
