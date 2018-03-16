package br.edu.ifrs.canoas.tads.tcc.repository;

import br.edu.ifrs.canoas.tads.tcc.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository repository;

    private final String USER_NAME = "User Name";

    @Test
    public void when_FindByThemeContaining_then_ReturnTermPaper(){

        // given
        User user = new User();
        user.setUsername(USER_NAME);
        entityManager.persist(user);
        entityManager.flush();

        // when
        Optional<User> found = repository.findByUsername(USER_NAME);

        // then
        assertThat(found.get().getUsername()).isEqualTo(USER_NAME);
    }

    @Test
    public void given_noData_when_FindByThemeContaining_then_ReturnEmptyList(){

        // given

        // when
        Optional<User> found = repository.findByUsername(USER_NAME);

        // then
        assertThat(found).isEmpty();
    }

}