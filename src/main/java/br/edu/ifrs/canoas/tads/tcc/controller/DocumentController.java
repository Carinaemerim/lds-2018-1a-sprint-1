package br.edu.ifrs.canoas.tads.tcc.controller;

import br.edu.ifrs.canoas.tads.tcc.config.Messages;
import br.edu.ifrs.canoas.tads.tcc.config.auth.UserImpl;
import br.edu.ifrs.canoas.tads.tcc.domain.Student;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.service.TermPaperService;
import br.edu.ifrs.canoas.tads.tcc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequestMapping("/document")
@Controller
@AllArgsConstructor
public class DocumentController {

	private final Messages messages;
	private final TermPaperService termPaperService;
	private final UserService userService;

	@GetMapping("/")
	public ModelAndView document(@AuthenticationPrincipal UserImpl activeUser) {
		ModelAndView mav = new ModelAndView("/document/document");
		mav.addObject("advisors", userService.getAdvisors());
		TermPaper termPaper = termPaperService.getLastOneByUser(activeUser.getUser());
		if (termPaper == null) {
			termPaper = new TermPaper();
		}
		mav.addObject("termPaper", termPaper);
		return mav;
	}

	@PostMapping(path = "/theme/submit")
	public ModelAndView saveThemeDraft(@AuthenticationPrincipal UserImpl activeUser, @Valid TermPaper termPaper,
			BindingResult bindingResult, RedirectAttributes redirectAttr) {
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("/document/document");
			mav.addObject("advisors", userService.getAdvisors());
			return mav;
		}
		termPaper.setAuthor((Student)activeUser.getUser());
		ModelAndView mav = new ModelAndView("redirect:/document/");
		mav.addObject("termPaper", termPaperService.saveThemeDraft(termPaper));
		redirectAttr.addFlashAttribute("message", messages.get("field.draft-saved"));
		return mav;
	}

	@PostMapping(path = "/theme/submit", params = "action=evaluation")
	public ModelAndView submitThemeForEvaluation(@AuthenticationPrincipal UserImpl activeUser,
			@Valid TermPaper termPaper, BindingResult bindingResult, RedirectAttributes redirectAttr) {
		if (termPaper.getAdvisor() == null) {
			bindingResult.addError(new FieldError("termPaper", "advisor", messages.get("field.not-null")));
		}
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("/document/document");
			mav.addObject("advisors", userService.getAdvisors());
			return mav;
		}
		termPaper.setAuthor((Student)activeUser.getUser());
		ModelAndView mav = new ModelAndView("redirect:/document/");
		mav.addObject("termPaper", termPaperService.submitThemeForEvaluation(termPaper));
		redirectAttr.addFlashAttribute("message", messages.get("theme.submited-for-evaluation"));
		return mav;
	}
}
