package br.edu.ifrs.canoas.tads.tcc.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ifrs.canoas.tads.tcc.domain.AcademicYear;
import br.edu.ifrs.canoas.tads.tcc.domain.Document;
import br.edu.ifrs.canoas.tads.tcc.domain.EvaluationStatus;
import br.edu.ifrs.canoas.tads.tcc.domain.Student;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.domain.User;
import br.edu.ifrs.canoas.tads.tcc.repository.AcademicYearRepository;
import br.edu.ifrs.canoas.tads.tcc.repository.DocumentRepository;
import br.edu.ifrs.canoas.tads.tcc.repository.ProfessorRepository;
import br.edu.ifrs.canoas.tads.tcc.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class TermPaperServiceTest {

	@Autowired
	TermPaperService service;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AcademicYearRepository academicYearRepository;

	@Autowired
	ProfessorRepository professorRepository;

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	EntityManager entityManager;

	private final String USER_LOGIN_DRAFT = "user3";
	private final String USER_LOGIN_NO_SUBMISSION = "userWithoutSubmission";
	private final String ACADEMIC_YEAR = "2018/01";

	// TODO nicolas.w - method to avoid code repetition
	private TermPaper loadDefaultData(AcademicYear academicYear, Optional<User> user) {
		TermPaper termPaper = service.getOneByUserAndAcademicYear(user.get(), academicYear);
		if (termPaper == null)
			termPaper = new TermPaper();
		termPaper.setTitle("Título Teste 1");
		termPaper.setTheme("Tema de teste");
		termPaper.setDescription("Descrição de teste 1");
		termPaper.setAcademicYear(academicYear);
		termPaper.setAuthor((Student) user.get());
		termPaper.setAdvisor(professorRepository.findAll().get(0));
		return termPaper;
	}

	// TODO nicolas.w
	@Test
	public void shouldReturnTermPaperByUserAndAcademicYear() throws Exception {
		// given
		Optional<User> userWithoutSubmission = userRepository.findByUsername(USER_LOGIN_DRAFT);
		AcademicYear academicYear = academicYearRepository.findFirstByTitle(ACADEMIC_YEAR);

		// when
		TermPaper termPaper = service.getOneByUserAndAcademicYear(userWithoutSubmission.get(), academicYear);

		// then
		assertThat(termPaper).isNotNull();
		assertThat(termPaper.getId()).isNotNull();
		assertThat(termPaper.getTheme()).isNotNull();
	}

	// TODO CdT005 - RNG021 nicolas.w
	@Test
	public void shouldNotSaveTermPaperWhenThemeIsEmpty() throws Exception {
		try {
			// given
			Optional<User> user = userRepository.findByUsername(USER_LOGIN_NO_SUBMISSION);
			AcademicYear academicYear = academicYearRepository.findFirstByTitle(ACADEMIC_YEAR);

			// when
			TermPaper termPaper = loadDefaultData(academicYear, user);
			termPaper.setTheme(" ");
			termPaper = service.submitThemeForEvaluation(termPaper);

			// then
		} catch (ConstraintViolationException constraintViolationException) {
			final ConstraintViolation<?> error = constraintViolationException.getConstraintViolations().iterator()
					.next();
			assertThat(error.getConstraintDescriptor().getAnnotation().annotationType()).isEqualTo(NotBlank.class);
			assertThat(error.getPropertyPath().toString()).isEqualTo("theme");
			assertThat(error.getMessage()).isEqualTo("Não pode estar em branco");
		}
	}

	// TODO CdT005 - RNG022 nicolas.w
	@Test
	public void shouldNotSaveTermPaperWhenThemeOverflow() throws Exception {
		try {
			// given
			Optional<User> user = userRepository.findByUsername(USER_LOGIN_NO_SUBMISSION);
			AcademicYear academicYear = academicYearRepository.findFirstByTitle(ACADEMIC_YEAR);

			// when
			TermPaper termPaper = loadDefaultData(academicYear, user);
			termPaper.setTheme("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
			termPaper = service.submitThemeForEvaluation(termPaper);

			// then
		} catch (ConstraintViolationException constraintViolationException) {
			final ConstraintViolation<?> error = constraintViolationException.getConstraintViolations().iterator()
					.next();
			assertThat(error.getConstraintDescriptor().getAnnotation().annotationType()).isEqualTo(Size.class);
			assertThat(error.getPropertyPath().toString()).isEqualTo("theme");
			assertThat(error.getMessage()).isEqualTo("tamanho deve estar entre 0 e 50");
		}
	}

	// TODO CdT005 - RNG037 nicolas.w
	@Test
	public void shouldCreateThemeDocumentWhenSubmitTermPaper() throws Exception {
		// given
		Optional<User> user = userRepository.findByUsername(USER_LOGIN_NO_SUBMISSION);
		AcademicYear academicYear = academicYearRepository.findFirstByTitle(ACADEMIC_YEAR);

		// when
		TermPaper termPaper = loadDefaultData(academicYear, user);
		termPaper = service.submitThemeForEvaluation(termPaper);
		entityManager.refresh(termPaper);

		// then
		assertThat(termPaper).isNotNull();
		Document document = termPaper.getThemeDocument();
		assertThat(document).isNotNull();
		assertThat(document.getStatus()).isNotNull();
		assertThat(document.getStatus()).isEqualTo(EvaluationStatus.IN_PROGRESS);
	}

	// TODO CdT005 - RNG023 nicolas.w
	@Test
	public void shouldDisapproveTermPaperWhenTimeExpiration() throws Exception {
		// given
		Optional<User> user = userRepository.findByUsername(USER_LOGIN_NO_SUBMISSION);
		AcademicYear academicYear = academicYearRepository.findFirstByTitle(ACADEMIC_YEAR);

		// when
		TermPaper termPaper = loadDefaultData(academicYear, user);
		termPaper = service.submitThemeForEvaluation(termPaper);
		entityManager.refresh(termPaper);
		assertThat(termPaper).isNotNull();
		Document document = termPaper.getThemeDocument();
		assertThat(document).isNotNull();
		assertThat(document.getStatus()).isNotNull();
		assertThat(document.getStatus()).isEqualTo(EvaluationStatus.IN_PROGRESS);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, -72);
		termPaper.getThemeDocument().setCreatedOn(cal.getTime());
		documentRepository.save(termPaper.getThemeDocument());
		termPaper = service.getOneByUserAndAcademicYear(user.get(), academicYear);

		// then
		assertThat(termPaper).isNotNull();
		document = termPaper.getThemeDocument();
		assertThat(document).isNotNull();
		assertThat(document.getStatus()).isNotNull();
		assertThat(document.getStatus()).isEqualTo(EvaluationStatus.DISAPPROVED);
	}

}