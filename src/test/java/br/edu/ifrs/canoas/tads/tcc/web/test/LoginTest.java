package br.edu.ifrs.canoas.tads.tcc.web.test;

import br.edu.ifrs.canoas.tads.tcc.web.config.MyFluentTest;
import br.edu.ifrs.canoas.tads.tcc.web.page.LoginPage;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class LoginTest extends MyFluentTest {

    @Page
    LoginPage loginPage;

    @Test
    public void checkLoginSucceed() {
        loginPage.go(port);
        loginPage.fillAndSubmitForm("user", "user")
                .awaitUntilFormDisappear();
        assertThat(window().title()).isEqualTo("Header");
    }

    @Test
    public void checkLoginFailed() {
        loginPage.go(port);
        loginPage.fillAndSubmitForm("wrongUser", "wrongPass");
        assertThat($(".alert")).hasSize(1);
        loginPage.isAt();
    }


}