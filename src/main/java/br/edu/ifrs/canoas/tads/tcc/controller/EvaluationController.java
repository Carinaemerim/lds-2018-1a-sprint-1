package br.edu.ifrs.canoas.tads.tcc.controller;

import br.edu.ifrs.canoas.tads.tcc.config.auth.UserImpl;
import br.edu.ifrs.canoas.tads.tcc.domain.DocumentType;
import br.edu.ifrs.canoas.tads.tcc.domain.Evaluation;
import br.edu.ifrs.canoas.tads.tcc.domain.Grade;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.service.DocumentService;
import br.edu.ifrs.canoas.tads.tcc.service.EvaluationService;
import br.edu.ifrs.canoas.tads.tcc.service.TermPaperService;
import lombok.AllArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final DocumentService documentService;
    private final TermPaperService termPaperService;

    @GetMapping("/list")
    public ModelAndView greetings(@AuthenticationPrincipal UserImpl activeUser) {
        ModelAndView mav = new ModelAndView("/evaluation/list");
        mav.addObject("termPapers", evaluationService.getTermPaperEvaluation(activeUser.getUser()));
        return mav;
    }


    @GetMapping("/theme/{id}")
    public ModelAndView theme(Model model, @PathVariable Long id) {
        ModelAndView mav = new ModelAndView("/evaluation/theme");
        mav.addObject("termPaper", termPaperService.getOneById(id));
        //model.addAttribute("action", "record");
        return mav;
    }

    @GetMapping("/proposal/{id}")
    public ModelAndView proposal(Model model, @PathVariable Long id) {
        ModelAndView mav = new ModelAndView("/evaluation/proposal");
        mav.addObject("termPaper", termPaperService.getOneById(id));
        //model.addAttribute("action", "record");
        return mav;
    }

    @GetMapping("/termpaper/{id}/{documentId}")
    public ModelAndView termpaper(Model model, @PathVariable Long id, @PathVariable Long documentId) {
        ModelAndView mav = new ModelAndView("/evaluation/termpaper");
        mav.addObject("termPaper", termPaperService.getOneById(id));
        mav.addObject("document", documentService.getOneById(documentId));

        /*Evaluation evaluation = evaluationService.getOneEvaluationById(501L);
        if (evaluation == null) {
            evaluation = new Grade();
        }
        mav.addObject("evaluation", evaluation);*/
        Evaluation grade = evaluationService.getOneGradeById(501L);
        if (grade == null) {
            grade = new Grade();
        }
        mav.addObject("grade", grade);
        //model.addAttribute("action", "record");
        return mav;
    }

}
