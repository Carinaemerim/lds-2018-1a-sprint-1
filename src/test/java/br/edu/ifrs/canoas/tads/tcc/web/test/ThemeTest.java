package br.edu.ifrs.canoas.tads.tcc.web.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.fluentlenium.core.annotation.Page;
import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.tads.tcc.web.config.MyFluentTest;
import br.edu.ifrs.canoas.tads.tcc.web.page.ThemePage;


public class ThemeTest extends MyFluentTest {

    @Page
    ThemePage themePage;

    @Before
    public void login(){
        super.loginUserWithoutTheme();
    }

    @Test
    public void checkSearchForBoot() {
        //Given
    	themePage.go(port);
    	themePage.isAt();

        //When
    	themePage.fillAndSubmitForm("boot")
                .awaitUntilResultsAppear();
        //and
    	themePage.openResult();


        //Then
        assertThat(window().title()).isEqualTo("Catálogo");
    }

}