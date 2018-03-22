package br.edu.ifrs.canoas.tads.tcc.web.test;

import br.edu.ifrs.canoas.tads.tcc.web.config.MyFluentTest;
import br.edu.ifrs.canoas.tads.tcc.web.page.CatalogPage;
import org.fluentlenium.core.annotation.Page;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CatalogTest extends MyFluentTest {

    @Page
    CatalogPage catalogPage;

    @Before
    public void login(){
        super.login();
    }

    @Test
    public void checkSearchForBoot() {
        //Given
        catalogPage.go(port);
        catalogPage.isAt();

        //When
        catalogPage.fillAndSubmitForm("boot")
                .awaitUntilResultsAppear();
        //and
        catalogPage.openResult();
        //and
        catalogPage.downloadFile();


        //Then
        assertThat(window().title()).isEqualTo("Catálogo");
        assertThat(catalogPage.getFirstResultDetails().displayed()).isTrue();
        assertThat(catalogPage.getFirstResultDetails().text()).containsIgnoringCase("Assim como o título, o resumo");
    }

}