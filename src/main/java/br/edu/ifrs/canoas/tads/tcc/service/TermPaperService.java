package br.edu.ifrs.canoas.tads.tcc.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.domain.User;
import br.edu.ifrs.canoas.tads.tcc.repository.TermPaperRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TermPaperService {

	private final TermPaperRepository termPaperRepository;

	public TermPaper saveDraft(TermPaper termPaper) {
		TermPaper fetchedTermPaper = this.getOne(termPaper);
		if (fetchedTermPaper == null || fetchedTermPaper.getId() == null)
			fetchedTermPaper = new TermPaper();
		fetchedTermPaper.setAdvisor(termPaper.getAdvisor());
		fetchedTermPaper.setAuthor(termPaper.getAuthor());
		fetchedTermPaper.setDescription(termPaper.getDescription());
		fetchedTermPaper.setTheme(termPaper.getTheme());
		fetchedTermPaper.setTitle(termPaper.getTitle());
		return termPaperRepository.save(termPaper);
	}

	public TermPaper getOne(TermPaper termPaper) {
		if (termPaper == null || termPaper.getId() == null)
			return null;
		Optional<TermPaper> optionalTermPaper = termPaperRepository.findById(termPaper.getId());
		return optionalTermPaper.isPresent() ? optionalTermPaper.get() : null;
	}

	public TermPaper getLastOneByUser(User user) {
		return termPaperRepository.findLastByAuthorId(user.getId());
	}

}
