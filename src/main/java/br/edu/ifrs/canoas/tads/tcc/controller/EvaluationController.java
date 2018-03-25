package br.edu.ifrs.canoas.tads.tcc.controller;

import br.edu.ifrs.canoas.tads.tcc.config.Messages;
import br.edu.ifrs.canoas.tads.tcc.config.auth.UserImpl;
import br.edu.ifrs.canoas.tads.tcc.domain.*;
import br.edu.ifrs.canoas.tads.tcc.service.DocumentService;
import br.edu.ifrs.canoas.tads.tcc.service.EvaluationService;
import br.edu.ifrs.canoas.tads.tcc.service.TermPaperService;
import lombok.AllArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

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
    private final Messages messages;

    @ModelAttribute("allEvaluationStatus")
    public List<EvaluationStatus> populateEvaluationStatus() {
        return Arrays.asList(EvaluationStatus.ALL);
    }

    @GetMapping("/")
    public ModelAndView home(@AuthenticationPrincipal UserImpl activeUser) {
        ModelAndView mav = new ModelAndView("/evaluation/list");
        Iterable<TermPaper> termPapers = evaluationService.getTermPaperEvaluation(activeUser.getUser());
        mav.addObject("termPapers", termPapers);
        //model.addAttribute("statusColorTheme", get)
        return mav;
    }


    @GetMapping("/theme/{id}")
    public ModelAndView theme(Model model, @PathVariable Long id, @AuthenticationPrincipal UserImpl activeUser) {
        ModelAndView mav = new ModelAndView("/evaluation/theme");
        TermPaper termPaper = termPaperService.getOneById(id);
        mav.addObject("termPaper", termPaper);
        Document document = termPaper.getThemeDocument();
        //mav.addObject("document", document);

        Evaluation advice = evaluationService.getOneEvaluation(document, activeUser.getUser());
        if (advice == null) {
            advice = new Advice();
            advice.setDocument(document);
        }

        mav.addObject("advice", advice);
        //model.addAttribute("action", "record");
        return mav;
    }

    @GetMapping("/proposal/{id}")
    public ModelAndView proposal(Model model, @PathVariable Long id, @AuthenticationPrincipal UserImpl activeUser) {
        ModelAndView mav = new ModelAndView("/evaluation/proposal");
        TermPaper termPaper = termPaperService.getOneById(id);
        mav.addObject("termPaper", termPaper);
        Document document = termPaper.getProposalDocument();
        mav.addObject("document", document);

        Evaluation advice = evaluationService.getOneEvaluation(document, activeUser.getUser());
        if (advice == null) {
            advice = new Advice();
        }
        mav.addObject("advice", advice);
        return mav;
    }

    @GetMapping("/termpaper/{id}")
    public ModelAndView termpaper(Model model, @PathVariable Long id, @AuthenticationPrincipal UserImpl activeUser) {
        ModelAndView mav = new ModelAndView("/evaluation/termpaper");
        TermPaper termPaper = termPaperService.getOneById(id);
        mav.addObject("termPaper", termPaper);
        mav.addObject("document", documentService.getOneById(termPaper.getTermPaperDocument().getId()));

        Document document = termPaper.getTermPaperDocument();
        Evaluation grade = evaluationService.getOneEvaluation(document, activeUser.getUser());
        if (grade == null) {
            grade = new Grade();
        }
        mav.addObject("grade", grade);

        //model.addAttribute("action", "record");
        return mav;
    }

    @PostMapping(path = "/theme/submit")
    public ModelAndView saveThemeDraft(@AuthenticationPrincipal UserImpl activeUser, Advice advice,
                                       RedirectAttributes redirectAttr) {
        ModelAndView mav = new ModelAndView("redirect:/evaluation/");
        advice.setAppraiser((Professor) activeUser.getUser());
        mav.addObject("advice", evaluationService.saveThemeEvaluationDraft(advice));
        redirectAttr.addFlashAttribute("message", messages.get("field.draft-saved"));
        return mav;
    }

    @PostMapping(path = "/theme/submit", params = "action=evaluation")
    public ModelAndView submitThemeForEvaluation(@AuthenticationPrincipal UserImpl activeUser,
                                                 @Valid Advice advice, BindingResult bindingResult, RedirectAttributes redirectAttr) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
           ModelAndView mav = new ModelAndView("/evaluation/theme/1001");
            //bindingResult.addError(new FieldError("advice", "considerations", messages.get("field.not-null")));
            return mav;
        }

        ModelAndView mav = new ModelAndView("redirect:/evaluation/");
        advice.setAppraiser((Professor) activeUser.getUser());
        mav.addObject("advice", evaluationService.saveThemeEvaluationFinal(advice));
        redirectAttr.addFlashAttribute("message", messages.get("field.saved"));

        return mav;
    }

    @PostMapping(path = "/proposal/submit", params = "action=evaluation")
    public ModelAndView submitProposalForEvaluation(@AuthenticationPrincipal UserImpl activeUser,
                                                    @Valid TermPaper termPaper, BindingResult bindingResult, RedirectAttributes redirectAttr) {

        ModelAndView mav = new ModelAndView("redirect:/evaluation/");

        return mav;
    }

    @PostMapping(path = "/proposal/submit")
    public ModelAndView saveProposalPaperDraft(@AuthenticationPrincipal UserImpl activeUser, @Valid TermPaper termPaper,
                                               BindingResult bindingResult, RedirectAttributes redirectAttr) {

        ModelAndView mav = new ModelAndView("redirect:/evaluation/");

        return mav;
    }

    @PostMapping(path = "/termpaper/submit")
    public ModelAndView saveTermPaperDraft(@AuthenticationPrincipal UserImpl activeUser, @Valid TermPaper termPaper,
                                           BindingResult bindingResult, RedirectAttributes redirectAttr) {

        ModelAndView mav = new ModelAndView("redirect:/evaluation/");

        return mav;
    }


    @PostMapping(path = "/termpaper/submit", params = "action=evaluation")
    public ModelAndView submitTermPaperForEvaluation(@AuthenticationPrincipal UserImpl activeUser,
                                                     @Valid TermPaper termPaper, BindingResult bindingResult, RedirectAttributes redirectAttr) {

        ModelAndView mav = new ModelAndView("redirect:/evaluation/");

        return mav;
    }


}
