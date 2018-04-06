package br.edu.ifrs.canoas.tads.tcc.controller;

import br.edu.ifrs.canoas.tads.tcc.config.auth.UserImpl;
import br.edu.ifrs.canoas.tads.tcc.domain.Message;
import br.edu.ifrs.canoas.tads.tcc.service.FileService;
import br.edu.ifrs.canoas.tads.tcc.service.MessageService;
import br.edu.ifrs.canoas.tads.tcc.service.TermPaperService;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/chat")
@Controller
@AllArgsConstructor
public class MessageController {
	private final MessageService messageService;
	private final TermPaperService termPaperService;


	@PostMapping("/send")
	public ModelAndView sendMessage(@AuthenticationPrincipal UserImpl activeUser,
                                    @RequestParam(value = "mFile", required = false) MultipartFile mFile,
                                    @ModelAttribute("messageChat") Message message) {
		ModelAndView mav = new ModelAndView("/document/fragments/chat :: chat-results");
		message.setSender(activeUser.getUser());
		message.setDate(new Date());
		
		if(activeUser.getUser().getUserType().equalsIgnoreCase("Student")) {
			message.setReceiver(termPaperService.getLastOneByUser(activeUser.getUser()).getAdvisor());
		}else {
			//message.setReceiver(receiver);TODO map receiver
		}

		if(mFile != null) {

			try {
				messageService.save(message, mFile);
			}catch(IOException e){ //não sei exatamente o que fazer nesse caso, deixarei assim apenas para funcionar no momento
				mav.addObject("messages", messageService.findAllBySenderOrReceiverOrderByDate(activeUser.getUser()));
				mav.addObject("user", activeUser.getUser());
				return  mav;
			}
		}

		mav.addObject("messages", messageService.findAllBySenderOrReceiverOrderByDate(activeUser.getUser()));
		mav.addObject("user", activeUser.getUser());
		return mav;
	}

}
