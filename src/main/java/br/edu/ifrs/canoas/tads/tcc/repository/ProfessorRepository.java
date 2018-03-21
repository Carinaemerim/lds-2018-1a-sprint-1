package br.edu.ifrs.canoas.tads.tcc.repository;

import br.edu.ifrs.canoas.tads.tcc.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by rodrigo on 2/21/17.
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}