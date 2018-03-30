package br.edu.ifrs.canoas.tads.tcc.controller;

import br.edu.ifrs.canoas.tads.tcc.config.auth.UserImpl;
import br.edu.ifrs.canoas.tads.tcc.domain.Message;
import br.edu.ifrs.canoas.tads.tcc.service.FileService;
import br.edu.ifrs.canoas.tads.tcc.service.MessageService;
import br.edu.ifrs.canoas.tads.tcc.service.TermPaperService;
import lombok.AllArgsConstructor;
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
	private final FileService fileService;

	@PostMapping("/send")
	public ModelAndView sendMessage(@AuthenticationPrincipal UserImpl activeUser,
                                    @RequestParam(value = "mFile", required = false) MultipartFile mFile,
                                    @ModelAttribute("messageChat") Message message) {
		ModelAndView mav = new ModelAndView("/document/fragments/chat :: chat-results");
		message.setSender(activeUser.getUser());
		//message.setReceiver(termPaperService.getOneByAuthor(activeUser.getUser()).getAdvisor());
		messageService.save(message);

//		CommonsMultipartFile fileForm = message.getFile();
//
//		File file = new File();
//		file.setFilename(fileForm.getOriginalFilename());
//		file.setContent(fileForm.getBytes());
//		file.setContentType(fileForm.getContentType());
//		file.setCreatedOn(new Date());
//		file.setDescription(fileForm.getStorageDescription());

//		fileService.save(file);

		mav.addObject("messages", messageService.findAllBySenderOrReceiverOrderByDate(activeUser.getUser()));
		mav.addObject("user", activeUser.getUser());
		return mav;
	}

}
