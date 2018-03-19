package br.edu.ifrs.canoas.tads.tcc.service;

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
    	return termPaperRepository.save(termPaper);
    }

	public TermPaper getLastOneByUser(User user) {
		return termPaperRepository.findLastByAuthorId(user.getId());
	}

}
