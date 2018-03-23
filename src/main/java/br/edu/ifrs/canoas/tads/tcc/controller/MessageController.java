package br.edu.ifrs.canoas.tads.tcc.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifrs.canoas.tads.tcc.config.auth.UserImpl;
import br.edu.ifrs.canoas.tads.tcc.domain.Message;
import br.edu.ifrs.canoas.tads.tcc.service.MessageService;
import br.edu.ifrs.canoas.tads.tcc.service.TermPaperService;
import lombok.AllArgsConstructor;

@RequestMapping("/chat")
@Controller
@AllArgsConstructor
public class MessageController {
	private final MessageService messageService;
	private final TermPaperService termPaperService;
	
	@PostMapping("/send")
	public String sendMessage(@AuthenticationPrincipal UserImpl activeUser, Message message) {
		message.setSender(activeUser.getUser());
		message.setReceiver(termPaperService.getOneByAuthor(activeUser.getUser()).getAdvisor());
		messageService.save(message);
		return "redirect:/document/";
	}
}
