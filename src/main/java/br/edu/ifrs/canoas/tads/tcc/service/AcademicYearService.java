package br.edu.ifrs.canoas.tads.tcc.service;

import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.tads.tcc.domain.AcademicYear;
import br.edu.ifrs.canoas.tads.tcc.repository.AcademicYearRepository;
import lombok.AllArgsConstructor;

/**
 * Created by cassiano on 3/21/18.
 */
@Service
@AllArgsConstructor
public class AcademicYearService {

	private final AcademicYearRepository academicYearRepository;
	private final TaskService taskService;

	public Iterable<AcademicYear> findAllByOrderByIdAsc() {
		return academicYearRepository.findAllByOrderByIdAsc();
	}

	public AcademicYear getCurrentAcademicYear() {
		return academicYearRepository.findFirstByTitle(taskService.getPeriod());
	}

}
