package br.edu.ifrs.canoas.tads.tcc.service;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.tads.tcc.config.Messages;
import br.edu.ifrs.canoas.tads.tcc.domain.AcademicYear;
import br.edu.ifrs.canoas.tads.tcc.domain.Advice;
import br.edu.ifrs.canoas.tads.tcc.domain.Document;
import br.edu.ifrs.canoas.tads.tcc.domain.EvaluationStatus;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.domain.User;
import br.edu.ifrs.canoas.tads.tcc.repository.TermPaperRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TermPaperService {

	private final Messages messages;

	private final TermPaperRepository termPaperRepository;

	private final AcademicYearService academicYearService;

	private final DocumentService documentService;

	private final EvaluationService evaluationService;

	public TermPaper getOneById(Long id) {
		return termPaperRepository.getOne(id);
	}

	public TermPaper getOne(TermPaper termPaper) {
		if (termPaper == null || termPaper.getId() == null)
			return null;
		Optional<TermPaper> optionalTermPaper = termPaperRepository.findById(termPaper.getId());
		return optionalTermPaper.isPresent() ? optionalTermPaper.get() : null;
	}

	public TermPaper saveThemeDraft(TermPaper termPaper) {
		TermPaper fetchedTermPaper = this.getOne(termPaper);
		if (fetchedTermPaper == null || fetchedTermPaper.getId() == null) {
			AcademicYear academicYear = academicYearService.getCurrentAcademicYear();
			if (academicYear == null) {
				throw new RuntimeException(messages.get("theme.academicYearNotDefined"));
			}
			fetchedTermPaper = new TermPaper();
			fetchedTermPaper.setAcademicYear(academicYear);
		}
		if (!fetchedTermPaper.getThemeSubmitted()) {
			fetchedTermPaper.setAdvisor(termPaper.getAdvisor());
			fetchedTermPaper.setTheme(termPaper.getTheme());
		}
		fetchedTermPaper.setAuthor(termPaper.getAuthor());
		fetchedTermPaper.setDescription(termPaper.getDescription());
		fetchedTermPaper.setTitle(termPaper.getTitle());
		return termPaperRepository.save(fetchedTermPaper);
	}

	public TermPaper submitThemeForEvaluation(TermPaper termPaper) {
		termPaper = this.saveThemeDraft(termPaper);
		documentService.createOrUpdateThemeDocument(termPaper);
		return getOne(termPaper);
	}

	public TermPaper getLastOneByUser(User user) {
		AcademicYear academicYear = academicYearService.getCurrentAcademicYear();
		if (academicYear == null) {
			throw new RuntimeException(messages.get("theme.academicYearNotDefined"));
		}
		return verifyThemeWaitingDuration(
				termPaperRepository.findFirstByAuthorIdAndAcademicYearOrderByIdDesc(user.getId(), academicYear));
	}

	public TermPaper getOneByUserAndAcademicYear(User user, AcademicYear academicYear) {
		if (academicYear == null) {
			throw new RuntimeException(messages.get("theme.academicYearNotDefined"));
		}
		TermPaper termPaper = verifyThemeWaitingDuration(
				termPaperRepository.findFirstByAuthorIdAndAcademicYearOrderByIdDesc(user.getId(), academicYear));
		return (termPaper == null) ? new TermPaper() : termPaper;
	}

	private TermPaper verifyThemeWaitingDuration(TermPaper termPaper) {
		// TODO RNG023 Nicolas
		if (termPaper != null) {
			Document themeDocument = termPaper.getThemeDocument();
			if (themeDocument != null && themeDocument.getStatus().equals(EvaluationStatus.IN_PROGRESS)
					&& themeDocument.getCreatedOn() != null) {
				long timeDiff = Calendar.getInstance().getTimeInMillis() - themeDocument.getCreatedOn().getTime();
				double diffInHours = ((double) timeDiff) / (1000 * 60 * 60);
				if (diffInHours > 72.0) {
					Advice advice = (Advice) evaluationService.getOneEvaluation(themeDocument, termPaper.getAdvisor());
					if (advice == null) {
						advice = new Advice();
						advice.setDocument(themeDocument);
						advice.setIsFinal(true);
						themeDocument.getEvaluations().add(advice);
					}
					advice.setAppraiser(termPaper.getAdvisor());
					advice.setStatus(EvaluationStatus.DISAPPROVED);
					advice.setConsiderations(messages.get("theme.evaluationTimeExpired"));
					advice = evaluationService.saveThemeEvaluationFinal(advice, true);
				}
			}
		}
		return termPaper;
	}
}
