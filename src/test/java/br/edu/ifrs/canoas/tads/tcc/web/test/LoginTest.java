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
        //Given
        loginPage.go(port);
        //When
        loginPage.fillAndSubmitForm("user", "user")
                .awaitUntilFormDisappear();
        //Then
        assertThat(window().title()).isEqualTo("Header");
    }

    @Test
    public void checkLoginFailed() {
        //Given
        loginPage.go(port);
        //When
        loginPage.fillAndSubmitForm("wrongUser", "wrongPass");
        //Then
        assertThat($(".alert")).hasSize(1);
        loginPage.isAt();
    }


}