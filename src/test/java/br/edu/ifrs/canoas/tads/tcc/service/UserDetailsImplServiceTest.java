package br.edu.ifrs.canoas.tads.tcc.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserDetailsImplServiceTest {

    @Autowired
    UserDetailsImplService service;

    private final String USERNAME="user";

    @Test
    public void shouldReturnUserDetails(){
        //given
        //having existing user

        //when
        UserDetails userDetails = service.loadUserByUsername(USERNAME);

        //then
        assertThat(userDetails).extracting("user.name").contains("Master Yoda");
        assertThat(userDetails.getAuthorities()).hasSize(2);
    }

    @Test
    public void shouldReturnNull(){
        //given
        //having existing user

        //when
        UserDetails userDetails = service.loadUserByUsername(null);

        //then
        assertThat(userDetails).isNull();
    }
}