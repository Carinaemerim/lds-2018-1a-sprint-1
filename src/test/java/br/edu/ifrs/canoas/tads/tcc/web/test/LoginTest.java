package br.edu.ifrs.canoas.tads.tcc.web.test;

import br.edu.ifrs.canoas.tads.tcc.web.page.LoginPage;
import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.hook.wait.Wait;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Wait
public class LoginTest extends FluentTest {

    @LocalServerPort
    public int port;

    @Page LoginPage loginPage;

    @Ignore
    public void checkLoginSucceed() {
        loginPage.go(port);
        loginPage.fillAndSubmitForm("user", "user");
        assertThat(window().title()).isEqualTo("Header");
    }

    @Test
    public void checkLoginFailed() {
        loginPage.go(port);
        loginPage.fillAndSubmitForm("wrongUser", "wrongPass");
        //assertThat($(".alert")).hasSize(1);
       // assertThat(loginPage).isAt();
    }


}