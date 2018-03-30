package br.edu.ifrs.canoas.tads.tcc.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.edu.ifrs.canoas.tads.tcc.service.ScheduleService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrs.canoas.tads.tcc.config.Messages;
import br.edu.ifrs.canoas.tads.tcc.config.auth.UserImpl;
import br.edu.ifrs.canoas.tads.tcc.domain.AcademicYear;
import br.edu.ifrs.canoas.tads.tcc.domain.Advice;
import br.edu.ifrs.canoas.tads.tcc.domain.Document;
import br.edu.ifrs.canoas.tads.tcc.domain.Evaluation;
import br.edu.ifrs.canoas.tads.tcc.domain.EvaluationStatus;
import br.edu.ifrs.canoas.tads.tcc.domain.Grade;
import br.edu.ifrs.canoas.tads.tcc.domain.Professor;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.repository.AcademicYearRepository;
import br.edu.ifrs.canoas.tads.tcc.repository.UserRepository;
import br.edu.ifrs.canoas.tads.tcc.service.DocumentService;
import br.edu.ifrs.canoas.tads.tcc.service.EvaluationService;
import br.edu.ifrs.canoas.tads.tcc.service.TermPaperService;
import lombok.AllArgsConstructor;

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
    private final UserRepository userRepository;
    private final AcademicYearRepository academicYearRepository;
    private final Messages messages;
    private final ScheduleService scheduleService;

    @ModelAttribute("allEvaluationStatus")
    public List<EvaluationStatus> populateEvaluationStatus() {
        return Arrays.asList(EvaluationStatus.ALL);
    }

    @GetMapping(value = {"/", "/{period}"})
    public ModelAndView home(@AuthenticationPrincipal UserImpl activeUser,
                             @RequestParam(value = "academicYear", required = false) AcademicYear academicYear,
                             @PathVariable Optional<String> period) {
        ModelAndView mav = new ModelAndView("/evaluation/list");
        System.out.println();
        String searchPeriod = "";
        if (period.isPresent()) {
            searchPeriod = period.get();
        }

        if (academicYear == null) {
            academicYear = (academicYearRepository.findFirstByTitle(scheduleService.getPeriod()));
            System.out.println("Academic: " + academicYear);
        }
        AcademicYear oldAcademicYear = academicYear;
        switch (searchPeriod) {
            case "previous":
                academicYear = (academicYearRepository.findFirstByTitle(scheduleService.previous(academicYear.getTitle())));
                break;
            case "next":
                academicYear = (academicYearRepository.findFirstByTitle(scheduleService.next(academicYear.getTitle())));
                break;
            default:
                academicYear = (academicYearRepository.findFirstByTitle(scheduleService.getPeriod()));
                mav.addObject("academicYear", academicYear);
        }
        if (academicYear == null)
            academicYear = oldAcademicYear;
        mav.addObject("academicYear", academicYear);
        Iterable<TermPaper> termPapers = evaluationService.getTermPaperEvaluation(activeUser.getUser(),
                academicYear.getId());
        mav.addObject("termPapers", termPapers);

        return mav;
    }

    @GetMapping("/theme/{id}")
    public ModelAndView theme(Model model, @PathVariable Long id, @AuthenticationPrincipal UserImpl activeUser) {
        ModelAndView mav = new ModelAndView("/evaluation/theme");
        TermPaper termPaper = termPaperService.getOneById(id);
        mav.addObject("termPaper", termPaper);
        Document document = termPaper.getThemeDocument();
        mav.addObject("document", document);

        // Evaluation advice = evaluationService.getOneEvaluation(document,
        // activeUser.getUser());
        Evaluation advice = evaluationService.getOneEvaluation(document,
                userRepository.getOne((termPaper.getAdvisor().getId())));
        if (advice == null) {
            advice = new Advice();
        }
        advice.setDocument(document);
        mav.addObject("advice", advice);
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
        advice.setDocument(document);
        mav.addObject("advice", advice);
        return mav;
    }

    @GetMapping("/termpaper/{id}")
    public ModelAndView termpaper(Model model, @PathVariable Long id, @AuthenticationPrincipal UserImpl activeUser) {
        ModelAndView mav = new ModelAndView("/evaluation/termpaper");
        TermPaper termPaper = termPaperService.getOneById(id);
        mav.addObject("termPaper", termPaper);
        Document document = termPaper.getTermPaperDocument();
        mav.addObject("document", document);

        Evaluation grade = evaluationService.getOneEvaluation(document, activeUser.getUser());
        if (grade == null) {
            grade = new Grade();
        }
        mav.addObject("grade", grade);
        grade.setDocument(document);
        return mav;
    }

    @PostMapping(path = "/advice/submit")
    public ModelAndView submitThemeForEvaluation(@AuthenticationPrincipal UserImpl activeUser,
                                                 @RequestParam(value = "documentId", required = false) Long documentId,
                                                 // @RequestParam(value = "termPaperId", required = false) Long termPaperId,
                                                 @RequestParam(value = "action", required = false) String action, @Valid Advice advice,
                                                 BindingResult bindingResult, RedirectAttributes redirectAttr) {

        Boolean isFinal = false;
        Document document = documentService.getOneById(documentId);
        Long termPaperId = document.getTermPaper().getId();

        ModelAndView mav;
        if (action.equals("evaluation"))
            isFinal = true;
        if (bindingResult.hasErrors()) {
            switch (document.getDocumentType()) {
                case THEME:
                    mav = new ModelAndView("/evaluation/theme");
                    break;
                case PROPOSAL:
                    mav = new ModelAndView("/evaluation/proposal");
                    break;
                default:
                    mav = new ModelAndView("redirect:/evaluation/");
                    return mav;
            }
            mav.addObject("termPaper", document.getTermPaper());
            advice.setDocument(document);

            mav.addObject("advice", advice);
            mav.addObject("document", document);
            return mav;
        }

        if (isFinal) {
            mav = new ModelAndView("redirect:/evaluation/");
        } else {
            switch (document.getDocumentType()) {
                case THEME:
                    mav = new ModelAndView("redirect:/evaluation/theme/" + termPaperId);
                    break;
                case PROPOSAL:
                    mav = new ModelAndView("redirect:/evaluation/proposal/" + termPaperId);
                    break;
                default:
                    mav = new ModelAndView("redirect:/evaluation/");
            }
        }
        advice.setAppraiser((Professor) activeUser.getUser());
        advice.setDocument(document);
        mav.addObject("advice", evaluationService.saveThemeEvaluationFinal(advice, isFinal));

        redirectAttr.addFlashAttribute("message",
                (isFinal) ? messages.get("field.saved") : messages.get("field.draft-saved"));

        return mav;
    }

    @PostMapping(path = "/grade/submit")
    public ModelAndView submitThemeForEvaluation(
            @AuthenticationPrincipal UserImpl activeUser,
            @RequestParam(value = "documentId", required = false) Long documentId,
            // @RequestParam(value = "termPaperId", required = false) Long termPaperId,
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "mFile", required = false) MultipartFile mFile, @Valid Grade grade,
            BindingResult bindingResult, RedirectAttributes redirectAttr) throws IOException {

        Boolean isFinal = false;
        Boolean delFile = false;
        Document document = documentService.getOneById(documentId);
        Long termPaperId = document.getTermPaper().getId();


        ModelAndView mav;
        if (action.equals("delFile")) {
            delFile = true;
        }
        if (action.equals("evaluation"))
            isFinal = true;
        if (bindingResult.hasErrors()) {
            switch (document.getDocumentType()) {
                case TERMPAPER:
                    mav = new ModelAndView("/evaluation/termpaper");
                    break;
                default:
                    mav = new ModelAndView("redirect:/evaluation/");
                    return mav;
            }
            mav.addObject("termPaper", document.getTermPaper());
            grade.setDocument(document);
            mav.addObject("grade", grade);
            mav.addObject("document", document);
            return mav;
        }

        if (isFinal) {
            mav = new ModelAndView("redirect:/evaluation/");
        } else {
            mav = new ModelAndView("redirect:/evaluation/termpaper/" + termPaperId);
        }
        grade.setAppraiser((Professor) activeUser.getUser());
        grade.setDocument(document);
        mav.addObject("grade", evaluationService.saveTermPaperEvaluationFinal(grade, isFinal, mFile, delFile));

        redirectAttr.addFlashAttribute("message",
                (isFinal) ? messages.get("field.saved") : messages.get("field.draft-saved"));

        return mav;
    }

}
