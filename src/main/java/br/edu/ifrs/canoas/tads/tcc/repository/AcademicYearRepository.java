package br.edu.ifrs.canoas.tads.tcc.repository;

import br.edu.ifrs.canoas.tads.tcc.domain.AcademicYear;
import br.edu.ifrs.canoas.tads.tcc.domain.Advice;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long> {

    List<AcademicYear> findAllByOrderByIdAsc();

}