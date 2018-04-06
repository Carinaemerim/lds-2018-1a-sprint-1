package br.edu.ifrs.canoas.tads.tcc.web.test;

import br.edu.ifrs.canoas.tads.tcc.web.config.MyFluentTest;
import br.edu.ifrs.canoas.tads.tcc.web.page.EvaluationPage;
import org.fluentlenium.core.annotation.Page;
import org.junit.Before;
import org.junit.Test;


public class EvaluationTest extends MyFluentTest {

    @Page
    EvaluationPage evaluationPage;

    @Before
    public void loginProfessor(){
        super.loginProfessor();
    }

    @Test
    public void checkListForBoot() {
        //Given
        evaluationPage.go(port);
        evaluationPage.isAt();
        evaluationPage.isAtTermPaperEvaluation();
        evaluationPage.awaitUntilTermPaperAppear();

       /* //When
        catalogPage.fillAndSubmitForm("boot")
                .awaitUntilResultsAppear();
        //and
        catalogPage.openResult();
        //and
        catalogPage.downloadFile();


        //Then
        assertThat(window().title()).isEqualTo("Catálogo");
        assertThat(catalogPage.getFirstResultDetails().displayed()).isTrue();
        assertThat(catalogPage.getFirstResultDetails().text()).containsIgnoringCase("Assim como o título, o resumo");*/
    }

}