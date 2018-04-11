package br.edu.ifrs.canoas.tads.tcc.web.test;

import br.edu.ifrs.canoas.tads.tcc.web.config.MyFluentTest;
import br.edu.ifrs.canoas.tads.tcc.web.page.EvaluationPage;
import org.fluentlenium.core.annotation.Page;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.AssertFalse;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.*;
import static org.fluentlenium.assertj.FluentLeniumAssertions.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class EvaluationTest extends MyFluentTest {

    @Page
    EvaluationPage evaluationPage;

    @Before
    public void loginProfessor(){
        super.loginProfessor();
    }

    @Test
    public void lastProfessorEvaluateTermPaper() {

        //Given
        evaluationPage.go(port);
        evaluationPage.isAt();
        evaluationPage.selectTermPaperForEvaluationLastProfessor()
                .awaitUntilFormEvaluationTermPaperAppear();
        evaluationPage.isAtTermPaperEvaluation();

        //When
        evaluationPage.fillTextAreaForm("Aprovado, revise o documento anexado");
        evaluationPage.fillAndSubmitForm("","9", "6", "7", "7", "5", "8","7", "8" , "9" , "10", "8", "7",evaluationPage.getFileAbsolutePath())
                .awaitUntilConfirmationModal();
        evaluationPage.selectConfirmSubmit()
                .awaitUntilTableListEvaluateAppear();

        //Then
        assertThat(window().title()).isEqualTo("Avaliação de Trabalhos");
        assertThat(evaluationPage.getGradeFinalLastProfessor().text()).containsIgnoringCase("7,6");
        assertThat(evaluationPage.getGradeStatusLastProfessor().text()).containsIgnoringCase("APROVADO");

    }

    @Test
    public void notLastProfessorEvaluateTermPaper() {

        //Given
        evaluationPage.go(port);
        evaluationPage.isAt();
        evaluationPage.selectTermPaperForEvaluationNotLastProfessor()
                .awaitUntilFormEvaluationTermPaperAppear();
        evaluationPage.isAtTermPaperEvaluation();

        //When
        evaluationPage.fillTextAreaForm("Aprovado, revise o documento anexado");
        evaluationPage.fillAndSubmitForm("","9", "6", "7", "7", "5", "8","7", "8" , "9" , "10", "8", "7",evaluationPage.getFileAbsolutePath())
                .awaitUntilConfirmationModal();
        evaluationPage.selectConfirmSubmit()
                .awaitUntilTableListEvaluateAppear();

        //Then
        assertThat(window().title()).isEqualTo("Avaliação de Trabalhos");
        assertThat(evaluationPage.getGradeFinalNotLastProfessor().text()).containsIgnoringCase("");
        assertThat(evaluationPage.getGradeStatusNotLastProfessor().text()).containsIgnoringCase("EM PROGRESSO");
        // todo add mensagem com sucesso

    }

    @Test
    public void saveDraftEvaluationTermPaper() {

        //Given
        evaluationPage.go(port);
        evaluationPage.isAt();
        evaluationPage.selectTermPaperForEvaluationLastProfessor()
                .awaitUntilFormEvaluationTermPaperAppear();
        evaluationPage.isAtTermPaperEvaluation();

        //When
        evaluationPage.fillTextAreaForm("Aprovado, revise o documento anexado");
        evaluationPage.fillAndSubmitDraftForm("","9", "6", "7", "7", "5", "8","7", "8" , "9" , "10", "8", "7",evaluationPage.getFileAbsolutePath())
        .awaitUntilAppraiserNameAppear();

        //Then
        assertThat(window().title()).isEqualTo("Avaliação de Monografia");
     /*   assertThat($(".div", withClass("callout callout-success lead")));
        //assertThat($(".textarea")).hasT("Aprovado, revise o documento anexado");
        assertThat(el("#considerations").text().equals("xAprovado, revise o documento anexado"));
        assertThat(!(el("#considerations").displayed()));
        //assertThat(el("#considerations").text().);*/
        assertThat(el("#date-and-hour").text().contains("??"));


        assertThat(evaluationPage.getDateAndHour());
        //ok
        assertThat(evaluationPage.getFinalGradeByProfessor().text()).containsIgnoringCase("7,6");



    }

}