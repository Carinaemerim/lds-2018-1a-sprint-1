package br.edu.ifrs.canoas.tads.tcc.service;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.domain.User;
import br.edu.ifrs.canoas.tads.tcc.repository.AcademicYearRepository;
import br.edu.ifrs.canoas.tads.tcc.repository.TermPaperRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TermPaperService {

    private final TermPaperRepository termPaperRepository;

    private final AcademicYearRepository academicYearRepository;

    private final DocumentService documentService;

    public TermPaper getOneById(Long id) {
        return termPaperRepository.getOne(id);
    }

    public TermPaper getOneByAuthor(User author) {
        return termPaperRepository .getOneByAuthor(author);
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
        	fetchedTermPaper = new TermPaper();
        	fetchedTermPaper.setAcademicYear(academicYearRepository.findFirstByOrderByIdDesc());
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
        return termPaperRepository.findFirstByAuthorId(user.getId(), Sort.by(Sort.Direction.DESC, "id"));
    }
}
