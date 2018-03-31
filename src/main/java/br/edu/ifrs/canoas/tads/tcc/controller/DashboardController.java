package br.edu.ifrs.canoas.tads.tcc.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrs.canoas.tads.tcc.config.auth.UserImpl;
import br.edu.ifrs.canoas.tads.tcc.domain.Advice;
import br.edu.ifrs.canoas.tads.tcc.domain.Document;
import br.edu.ifrs.canoas.tads.tcc.domain.Evaluation;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.repository.UserRepository;
import br.edu.ifrs.canoas.tads.tcc.service.EvaluationService;
import br.edu.ifrs.canoas.tads.tcc.service.ScheduleService;
import br.edu.ifrs.canoas.tads.tcc.service.TermPaperService;
import br.edu.ifrs.canoas.tads.tcc.service.UserService;

/**
 * Created by elvis on 03/14/18.
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	private final ScheduleService scheduleService;
	private final TermPaperService termPaperService;
	private final EvaluationService evaluationService;
	private final UserService userService;
	private final UserRepository userRepository;
	
	
	public DashboardController(ScheduleService scheduleService, TermPaperService termPaperService, EvaluationService evaluationService, UserService userService, UserRepository userRepository) {
		super();
		this.scheduleService = scheduleService;
		this.termPaperService = termPaperService;
		this.evaluationService = evaluationService;
		this.userService = userService;
		this.userRepository = userRepository;
	}



	@GetMapping("/index")
	public ModelAndView greetings(@AuthenticationPrincipal UserImpl activeUser) {//Long id
		ModelAndView mav = new ModelAndView("/dashboard/index");
//		mav.addObject("user", userService.getOne(activeUser.getUser()));
		mav.addObject("tasks",scheduleService.listAll());
		Evaluation academicYear;
		//mav.addObject("status",scheduleService.getTaskStatus(id));
        Iterable<TermPaper> termPapers = evaluationService.getTermPaperEvaluation(activeUser.getUser(), null);
        mav.addObject("termPapers", termPapers);
		return mav;
	
	}
	
    @GetMapping("/theme/{id}")
    public ModelAndView theme(@PathVariable Long id, @AuthenticationPrincipal UserImpl activeUser) {
        ModelAndView mav = new ModelAndView("/evaluation/theme");
        TermPaper termPaper = termPaperService.getOneById(id);
        mav.addObject("termPaper", termPaper);
        Document document = termPaper.getThemeDocument();
        mav.addObject("document", document);

        Evaluation advice = evaluationService.getOneEvaluation(document,
                userRepository.getOne((termPaper.getAdvisor().getId())));
        if (advice == null) {
            advice = new Advice();
        }
        advice.setDocument(document);
        mav.addObject("advice", advice);
        return mav;
    }

	

}