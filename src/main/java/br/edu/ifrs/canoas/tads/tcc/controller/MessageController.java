package br.edu.ifrs.canoas.tads.tcc.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView sendMessage(@AuthenticationPrincipal UserImpl activeUser, Message message) {
		ModelAndView mav = new ModelAndView("/document/fragments/chat :: chat-results");
		message.setSender(activeUser.getUser());
		message.setReceiver(termPaperService.getOneByAuthor(activeUser.getUser()).getAdvisor());
		messageService.save(message);
		mav.addObject("messages", messageService.findAllBySenderOrReceiverOrderByDate(activeUser.getUser()));
		mav.addObject("user", activeUser.getUser());
		return mav;
	}
}
