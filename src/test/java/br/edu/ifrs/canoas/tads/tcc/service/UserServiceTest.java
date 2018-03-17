package br.edu.ifrs.canoas.tads.tcc.service;

import br.edu.ifrs.canoas.tads.tcc.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class UserServiceTest {

    @Autowired
    UserService service;

    private final String PASSWORD = "password";

    @Test
    public void testSaveExistingUserIgnoringPassword(){
        // given
        User user = new User();
        user.setId(100L);
        user.setPassword(PASSWORD);

        // when
        User saved = service.save(user);

        // then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getPassword()).isNotEqualTo(PASSWORD);
    }

    @Test
    public void testSaveNotExistingUser(){
        // given
        User user = new User();
        user.setId(1L);
        user.setPassword(PASSWORD);

        // when
        User saved = service.save(user);

        // then
        assertThat(saved).isNull();
    }

    @Test
    public void testSaveWithNullValue(){
        // given
        User user = new User();
        user.setId(1L);
        user.setPassword(PASSWORD);

        // when
        User saved = service.save(null);

        // then
        assertThat(saved).isNull();
    }



}