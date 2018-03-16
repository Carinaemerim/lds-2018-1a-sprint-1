package br.edu.ifrs.canoas.tads.tcc.service;

import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.repository.TermPaperRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CatalogService {

    private final TermPaperRepository termPaperRepository;

    public Iterable<TermPaper> search(String criteria) {
        return termPaperRepository.findByThemeContainingIgnoreCase(criteria);
    }

}
