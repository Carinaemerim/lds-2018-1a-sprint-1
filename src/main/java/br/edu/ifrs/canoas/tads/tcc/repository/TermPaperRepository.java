package br.edu.ifrs.canoas.tads.tcc.repository;

import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermPaperRepository extends JpaRepository<TermPaper, Long> {

	List<TermPaper> findByThemeContainingIgnoreCase(String theme);

	TermPaper findFirstByAuthorId(Long id, Sort sort);
}
