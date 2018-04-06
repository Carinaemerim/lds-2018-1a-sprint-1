package br.edu.ifrs.canoas.tads.tcc.controller;

import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrs.canoas.tads.tcc.config.Messages;
import br.edu.ifrs.canoas.tads.tcc.config.auth.UserImpl;
import br.edu.ifrs.canoas.tads.tcc.domain.AcademicYear;
import br.edu.ifrs.canoas.tads.tcc.domain.EvaluationBoard;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.repository.AcademicYearRepository;
import br.edu.ifrs.canoas.tads.tcc.repository.UserRepository;
import br.edu.ifrs.canoas.tads.tcc.service.DocumentService;
import br.edu.ifrs.canoas.tads.tcc.service.EvaluationBoardService;
import br.edu.ifrs.canoas.tads.tcc.service.EvaluationService;
import br.edu.ifrs.canoas.tads.tcc.service.TaskService;
import br.edu.ifrs.canoas.tads.tcc.service.TermPaperService;
import br.edu.ifrs.canoas.tads.tcc.service.UserService;
import lombok.Data;

@RequestMapping("/assessment-bank")
@Controller
@Data
public class AssessmentBankController {
	
	private final UserService userService;
    private final TermPaperService termPaperService;
    private final EvaluationBoardService evaluationBoardService;
    private final EvaluationService evaluationService;
    private final DocumentService documentService;
    private final UserRepository userRepository;
    private final AcademicYearRepository academicYearRepository;
    private final Messages messages;
    private final TaskService taskService;
    
	@GetMapping("/list")
    public ModelAndView home(@AuthenticationPrincipal UserImpl activeUser,
                             @PathVariable Optional<Long> academicYearId,
                             @PathVariable Optional<String> period) {
        ModelAndView mav = new ModelAndView("/assessment-bank/list");

        AcademicYear academicYear;
        String searchPeriod = "";
        Optional<AcademicYear> opAcademicYear;
        Long searchAcademicYearId;
        if (period.isPresent() && academicYearId.isPresent()) {
            searchPeriod = period.get();
            searchAcademicYearId = academicYearId.get();
            opAcademicYear = (academicYearRepository.findFirstByIdIs(searchAcademicYearId));
            if(opAcademicYear.isPresent()) {
                academicYear = opAcademicYear.get();
                System.out.println("Acdmic acho na pesquisa ");
            } else {
                System.out.println("Não acho na pesquisa ");
                academicYear = (academicYearRepository.findFirstByTitle(taskService.getPeriod()));
            }


        } else {
            System.out.println("Academic not presente ,pegou current ");
            academicYear = (academicYearRepository.findFirstByTitle(taskService.getPeriod()));
        }

        AcademicYear oldAcademicYear = academicYear;
        switch (searchPeriod) {
            case "previous":
                academicYear = (academicYearRepository.findFirstByTitle(taskService.previous(academicYear.getTitle())));
                break;
            case "next":
                academicYear = (academicYearRepository.findFirstByTitle(taskService.next(academicYear.getTitle())));
                break;
            default:
                academicYear = (academicYearRepository.findFirstByTitle(taskService.getPeriod()));
                mav.addObject("academicYear", academicYear);
        }
        /*if (academicYear == null)
            academicYear = oldAcademicYear;*/
        mav.addObject("isNext", evaluationService.getNextPeriod(academicYear));
        mav.addObject("isPrevious", evaluationService.getPreviousPeriod(academicYear));
        mav.addObject("academicYear", academicYear);
        Iterable<TermPaper> termPapers = evaluationService.getTermPaperEvaluation(activeUser.getUser(),
                academicYear.getId());
        mav.addObject("termPapers", termPapers);

        return mav;
    }
	
	@GetMapping("/listprofessors")
	public ModelAndView selectassessmentBank() {		
		ModelAndView mav = new ModelAndView("/assessment-bank/listprofessors");
		mav.addObject("professors", userService.getAdvisors());
		return mav;
	}

	/*
	 *@PostMapping("/listprofessors")
	public String save(EvaluationBoard evaluationBoard) {
		evaluationBoardService.save(evaluationBoard);
		return "redirect:/assessment-bank/listprofessors";
	}	
	*/
}