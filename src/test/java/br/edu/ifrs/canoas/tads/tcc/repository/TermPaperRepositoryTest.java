package br.edu.ifrs.canoas.tads.tcc.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ifrs.canoas.tads.tcc.domain.AcademicYear;
import br.edu.ifrs.canoas.tads.tcc.domain.Student;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TermPaperRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private TermPaperRepository repository;

	private final String THEME = "Java Rules!";
	private final String SHORT_THEME = "JAVA";

	@Test
	public void whenFindByThemeContaining_thenReturnTermPaper() {

		// given
		TermPaper paper = new TermPaper();
		paper.setTheme(THEME);
		paper.setDescription(THEME);
		paper.setTitle(THEME);
		entityManager.persist(paper);
		entityManager.flush();

		// when
		Iterable<TermPaper> found = repository.findByThemeContainingIgnoreCase(SHORT_THEME);

		// then
		assertThat(found).hasSize(1).contains(paper).extracting("theme").contains("Java Rules!");
	}

	@Test
	public void whenFindByThemeContaining_thenReturnNoData() {

		// given

		// when
		Iterable<TermPaper> found = repository.findByThemeContainingIgnoreCase(SHORT_THEME);

		// then
		assertThat(found).isEmpty();
	}

	// TODO CdT005 (RNG023 - RNG037) - nicolas.w
	@Test
	public void whenFindOneByAuthorIdAndAcademicYear_thenReturnTermPaper() {
		// given
		AcademicYear academicYear = new AcademicYear();
		academicYear.setTitle("1998/01");
		academicYear = entityManager.persist(academicYear);

		Student author = new Student();
		author.setName("test_user_repo");
		author = entityManager.persist(author);

		TermPaper paper = new TermPaper();
		paper.setTheme(THEME);
		paper.setDescription(THEME);
		paper.setTitle(THEME);
		paper.setAcademicYear(academicYear);
		paper.setAuthor(author);
		entityManager.persist(paper);
		entityManager.flush();

		// when
		TermPaper found = repository.findFirstByAuthorIdAndAcademicYearOrderByIdDesc(author.getId(), academicYear);

		// then
		assertThat(found).isNotNull().extracting("theme").contains("Java Rules!");
	}
}