package br.edu.ifrs.canoas.tads.tcc.web.test;

import br.edu.ifrs.canoas.tads.tcc.web.config.MyFluentTest;
import br.edu.ifrs.canoas.tads.tcc.web.page.EvaluationPage;
import org.fluentlenium.core.annotation.Page;
import org.junit.Before;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;


public class EvaluationTest extends MyFluentTest {

    @Page
    EvaluationPage evaluationPage;

    @Before
    public void loginProfessor(){
        super.loginProfessor();
    }


    @Test
    public void asaveDraftEvaluationTermPaper() {

        //Given
        evaluationPage.go(port);
        evaluationPage.isAt();
        evaluationPage.selectTermPaperForEvaluationLastProfessor()
                .awaitUntilFormEvaluationTermPaperAppear();
        evaluationPage.isAtTermPaperEvaluation();
        evaluationPage.awaitTwoSeconds();

        //When
        evaluationPage.fillTextAreaForm("Aprovado, revise o documento anexado");
        evaluationPage.fillAndSubmitDraftForm("","9", "6", "7", "7", "5", "8","7", "8" , "9" , "10", "8", "7",evaluationPage.getFileAbsolutePath())
                .awaitUntilAppraiserNameAppear();

        //Then
        assertThat(window().title()).isEqualTo("Avaliação de Monografia");
        assertThat($(".callout")).hasSize(1);
        assertThat(evaluationPage.getDateAndHour().text()).startsWith(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        assertThat(evaluationPage.getFinalGradeByProfessor().text()).containsIgnoringCase("7,6");
    }



    @Test
    public void lastProfessorEvaluateTermPaper() {

        //Given
        evaluationPage.go(port);
        evaluationPage.isAt();
        evaluationPage.selectTermPaperForEvaluationLastProfessor()
                .awaitUntilFormEvaluationTermPaperAppear();
        evaluationPage.isAtTermPaperEvaluation();
        // e o professor for o último professor associado a avaliar este trabalho
        // e o outro avaliador informou os dados da avaliação escrita com 9, 6, 7, 7, 5 e 8
        // e a apresentação oral com os valores 7, 8 , 9 , 10, 8  e 7

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

    }




    @Test
    public void studentNotPresentation() {

        //Given
        evaluationPage.go(port);
        evaluationPage.isAt();
        evaluationPage.selectTermPaperForEvaluationLastProfessorTwo()
                .awaitUntilFormEvaluationTermPaperAppear();
        evaluationPage.isAtTermPaperEvaluation();
        evaluationPage.awaitTwoSeconds();
        // e o professor for o último professor associado a avaliar este trabalho
        // e o outro avaliador informou os dados da avaliação escrita com 9, 6, 7, 7, 5 e 8
        // e a apresentação oral com os valores 7, 8 , 9 , 10, 8  e 7

        //When
        evaluationPage.fillTextAreaForm("Não apresentou o trabalho");
        evaluationPage.fillAndSubmitForm("","0", "0", "0", "0", "0", "0","0", "0" , "0" , "0", "0", "0",evaluationPage.getFileAbsolutePath())
                .awaitUntilConfirmationModal();
        evaluationPage.selectConfirmSubmit()
                .awaitUntilTableListEvaluateAppear();

        //Then
        assertThat(window().title()).isEqualTo("Avaliação de Trabalhos");
        assertThat(evaluationPage.getGradeFinalLastProfessorTwo().text()).containsIgnoringCase("0,0");
        assertThat(evaluationPage.getGradeStatusLastProfessorTwo().text()).containsIgnoringCase("REPROVADO");
    }


    @Test
    public void proposalEvaluationRedo() {

        //Given
        evaluationPage.go(port);
        evaluationPage.isAt();
        evaluationPage.selectProposalForEvaluationLastProfessor()
                .awaitUntilFormEvaluationAdviceAppear();
        evaluationPage.isAtProposalEvaluation();
        // e o professor for o último professor associado a avaliar este trabalho
        // e o outro avaliador aprovou a proposta

        //When
        evaluationPage.fillTextAreaForm("Não apresentou o trabalho");
        evaluationPage.selectRadioEvaluateRedoAndSubmit()
                .awaitUntilConfirmationModal();
        evaluationPage.selectConfirmSubmit()
                .awaitUntilTableListEvaluateAppear();

        //Then
        assertThat(window().title()).isEqualTo("Avaliação de Trabalhos");
        assertThat(evaluationPage.getProposalStatusLastProfessor().text()).containsIgnoringCase("REFAZER");
    }

}