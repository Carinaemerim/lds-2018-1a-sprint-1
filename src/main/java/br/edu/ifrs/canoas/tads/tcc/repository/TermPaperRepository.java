package br.edu.ifrs.canoas.tads.tcc.repository;

import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TermPaperRepository extends JpaRepository<TermPaper, Long> {

	List<TermPaper> findByThemeContainingIgnoreCase(String theme);

	TermPaper findFirstByAuthorId(Long id, Sort sort);
	
	//@Query("FROM TermPaper t join Document d on (t.id = d.termPaper.id) join EvaluationBoard eb on eb.document.id = d.id join EvaluationBoardProfessors ebp on (ebp.evaluationBoard.id = eb.id)  and ebp.professors.id in (101)")
	@Query(value="SELECT term_paper.* FROM\n" +
			"\n" + 
			"     term_paper join document\n" + 
			"\n" + 
			"             on (term_paper.id = document.term_paper_id)\n" + 
			"\n" + 
			"     join evaluation_board\n" + 
			"\n" + 
			"             on evaluation_board.document_id = document.id\n" + 
			"\n" + 
			"     join evaluation_board_professors\n" + 
			"\n" + 
			"             on (evaluation_board_professors.evaluation_board_id = evaluation_board.id\n" + 
			"\n" + 
			"                and evaluation_board_professors.professors_id in (101) );", nativeQuery=true)
	//@Query("FROM TermPaper t join Document d on (t.id = d.termPaper.id) join EvaluationBoard eb on eb.document.id = d.id")// where eb.professors.id in (101)")
	//@Query("FROM TermPaper t join Document d on (t.id = d.termPaper.id) join EvaluationBoard eb on eb.document.id = d.id join eb.professors p where p.id in (101)")
	List<TermPaper> getTermPaperForEvaluation();
	
	TermPaper getOneByAuthor(User author);
	
}
