package br.edu.ifrs.canoas.tads.tcc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrs.canoas.tads.tcc.config.Messages;
import br.edu.ifrs.canoas.tads.tcc.config.auth.UserImpl;
import br.edu.ifrs.canoas.tads.tcc.domain.DocumentType;
import br.edu.ifrs.canoas.tads.tcc.domain.Message;
import br.edu.ifrs.canoas.tads.tcc.domain.Student;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.service.DocumentService;
import br.edu.ifrs.canoas.tads.tcc.service.MessageService;
import br.edu.ifrs.canoas.tads.tcc.service.TermPaperService;
import br.edu.ifrs.canoas.tads.tcc.service.UserService;
import lombok.AllArgsConstructor;

@RequestMapping("/document")
@Controller
@AllArgsConstructor
public class DocumentController {

	private final Messages messages;
	private final TermPaperService termPaperService;
	private final UserService userService;
	private final DocumentService documentService;
	private final MessageService messageService;

	@GetMapping("/")
	public ModelAndView document(@AuthenticationPrincipal UserImpl activeUser) {
		ModelAndView mav = new ModelAndView("/document/document");

		mav.addObject("messages", messageService.findAllBySenderOrReceiverOrderByDate(activeUser.getUser()));
		mav.addObject("messageChat", new Message());
		mav.addObject("advisors", userService.getAdvisors());
		mav.addObject("user", activeUser.getUser());
		mav.addObject("monographs", documentService.search(DocumentType.TERMPAPER));

		TermPaper termPaper = termPaperService.getLastOneByUser(activeUser.getUser());
		if (termPaper == null) {
			termPaper = new TermPaper();
		}
		mav.addObject("termPaper", termPaper);

		return mav;
	}

	@GetMapping("/delete/{id}")
	public ModelAndView deleteOne(@PathVariable Long id) {
		documentService.deleteOne(id);
		return new ModelAndView("redirect:" + "/document/");

	}

	private void authorIsStudentValidation(UserImpl activeUser, TermPaper termPaper, BindingResult bindingResult) {
		if (activeUser.getUser() instanceof Student) {
			termPaper.setAuthor((Student) activeUser.getUser());
		} else {
			bindingResult.addError(new FieldError("termPaper", "author", messages.get("theme.formOnlyForStudent")));
		}
	}

	@PostMapping(path = "/theme/submit")
	public ModelAndView saveThemeDraft(@AuthenticationPrincipal UserImpl activeUser, @Valid TermPaper termPaper,
			BindingResult bindingResult, RedirectAttributes redirectAttr) {
		authorIsStudentValidation(activeUser, termPaper, bindingResult);
		if (bindingResult.hasErrors()) {
			return this.document(activeUser).addObject("termPaper", termPaper);
		}
		ModelAndView mav = new ModelAndView("redirect:/document/");
		mav.addObject("termPaper", termPaperService.saveThemeDraft(termPaper));
		redirectAttr.addFlashAttribute("message", messages.get("field.draft-saved"));
		return mav;
	}

	@PostMapping(path = "/theme/submit", params = "action=evaluation")
	public ModelAndView submitThemeForEvaluation(@AuthenticationPrincipal UserImpl activeUser,
			@Valid TermPaper termPaper, BindingResult bindingResult, RedirectAttributes redirectAttr) {
		TermPaper fetchedTermPaper = termPaperService.getOne(termPaper);
		authorIsStudentValidation(activeUser, termPaper, bindingResult);
		if (termPaper.getAdvisor() == null && (fetchedTermPaper == null || !fetchedTermPaper.getThemeSubmitted())) {
			bindingResult.addError(new FieldError("termPaper", "advisor", messages.get("field.not-null")));
		}
		if (bindingResult.hasErrors()) {
			return this.document(activeUser).addObject("termPaper", termPaper);
		}
		termPaper.setAuthor((Student) activeUser.getUser());
		ModelAndView mav = new ModelAndView("redirect:/document/");
		mav.addObject("termPaper", termPaperService.submitThemeForEvaluation(termPaper));
		redirectAttr.addFlashAttribute("message", messages.get("theme.submited-for-evaluation"));
		return mav;
	}

	@RequestMapping(value = "Upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("file");
		return "redirect:upload-success";
	}

	@GetMapping("/monograph")
	public ModelAndView loadMonograph(@AuthenticationPrincipal UserImpl activeUser) {
		ModelAndView mav = new ModelAndView("/document/fragments/monograph_proposal :: document-list");
		
		mav.addObject("messages", messageService.findAllBySenderOrReceiverOrderByDate(activeUser.getUser()));
		mav.addObject("messageChat", new Message());
		mav.addObject("user", activeUser.getUser());
		
		mav.addObject("documents", documentService.search(DocumentType.TERMPAPER));

		TermPaper termPaper = termPaperService.getLastOneByUser(activeUser.getUser());
		if (termPaper == null) {
			termPaper = new TermPaper();
		}
		mav.addObject("termPaper", termPaper);
		
		return mav;
	}
	
	@GetMapping("/proposal")
	public ModelAndView loadProposal(@AuthenticationPrincipal UserImpl activeUser) {
		ModelAndView mav = new ModelAndView("/document/fragments/monograph_proposal :: document-list");
		
		mav.addObject("messages", messageService.findAllBySenderOrReceiverOrderByDate(activeUser.getUser()));
		mav.addObject("messageChat", new Message());
		mav.addObject("user", activeUser.getUser());
		
		mav.addObject("documents", documentService.search(DocumentType.PROPOSAL));

		TermPaper termPaper = termPaperService.getLastOneByUser(activeUser.getUser());
		if (termPaper == null) {
			termPaper = new TermPaper();
		}
		mav.addObject("termPaper", termPaper);
		
		return mav;
	}
	
	@GetMapping("/theme")
	public ModelAndView loadTheme(@AuthenticationPrincipal UserImpl activeUser) {
		ModelAndView mav = new ModelAndView("/document/fragments/theme :: document-list");
		
		mav.addObject("theme", termPaperService.getLastOneByUser(activeUser.getUser()));

		TermPaper termPaper = termPaperService.getLastOneByUser(activeUser.getUser());
		if (termPaper == null) {
			termPaper = new TermPaper();
		}
		mav.addObject("termPaper", termPaper);
		
		return mav;
	}
}
