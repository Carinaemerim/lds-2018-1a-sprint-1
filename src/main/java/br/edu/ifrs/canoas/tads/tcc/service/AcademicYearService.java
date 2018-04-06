package br.edu.ifrs.canoas.tads.tcc.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

	public AcademicYear getAcademicYearByIdOrPeriod(Optional<Long> academicYearId, Optional<String> period) {
		AcademicYear academicYear;
		String searchPeriod = "";
		Optional<AcademicYear> opAcademicYear;
		Long searchAcademicYearId;
		if (period.isPresent() && academicYearId.isPresent()) {
			searchPeriod = period.get();
			searchAcademicYearId = academicYearId.get();
			opAcademicYear = (academicYearRepository.findFirstByIdIs(searchAcademicYearId));
			if (opAcademicYear.isPresent()) {
				academicYear = opAcademicYear.get();
			} else {
				academicYear = (academicYearRepository.findFirstByTitle(taskService.getPeriod()));
			}

		} else {
			academicYear = (academicYearRepository.findFirstByTitle(taskService.getPeriod()));
		}

		switch (searchPeriod) {
		case "previous":
			academicYear = (academicYearRepository.findFirstByTitle(taskService.previous(academicYear.getTitle())));
			break;
		case "next":
			academicYear = (academicYearRepository.findFirstByTitle(taskService.next(academicYear.getTitle())));
			break;
		default:
			academicYear = (academicYearRepository.findFirstByTitle(taskService.getPeriod()));
		}
		return academicYear;
	}
}
