package br.edu.ifrs.canoas.tads.tcc.repository;

import br.edu.ifrs.canoas.tads.tcc.domain.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Created by rodrigo on 2/21/17.
 */
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    Evaluation findLastByDocumentIdAndAppraiserId(Long documentId, Long professorId);

}