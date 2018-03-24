package br.edu.ifrs.canoas.tads.tcc.repository;

import br.edu.ifrs.canoas.tads.tcc.domain.Evaluation;
import br.edu.ifrs.canoas.tads.tcc.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by rodrigo on 2/21/17.
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    Grade findLastByDocumentIdAndAppraiserId(Long documentId, Long professorId);

}