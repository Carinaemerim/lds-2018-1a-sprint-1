package br.edu.ifrs.canoas.tads.tcc.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ifrs.canoas.tads.tcc.domain.Task;
import br.edu.ifrs.canoas.tads.tcc.domain.TypeEvaluation;

/**
 * Created by Mariele on 3/03/18.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TaskRepository repository;
 
    private final String PERIOD = "01/2018";
    private final String DESCRIPTION = "DESCRIPTION";
    private final String TITLE = "TITLE";
    private final LocalDate DEADLINE = LocalDate.now();
    private final TypeEvaluation TYPEEVALUTION = TypeEvaluation.NOTAPPLICABLE;

    @Test
    public void whenFindByPeriod_thenReturnActualPeriod(){

        // given
        Task task = new Task();
        task.setPeriod(PERIOD);
        task.setDescription(DESCRIPTION);
        task.setTitle(TITLE);
        task.setDeadline(DEADLINE);
        task.setTypeEvaluation(TYPEEVALUTION);
        entityManager.persist(task);
        entityManager.flush();

        // when
        Iterable<Task> found = repository.findByPeriod(PERIOD);

        // then
        assertThat(found).contains(task)
                .extracting("period").contains(PERIOD);
    }
/*
    @Test
    public void whenFindByThemeContaining_thenReturnNoData(){

        // given

        // when
        Iterable<TermPaper> found = repository.findByThemeContainingIgnoreCase(SHORT_THEME);

        // then
        assertThat(found).isEmpty();
    }
*/
}
