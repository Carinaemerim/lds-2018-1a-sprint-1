package br.edu.ifrs.canoas.tads.tcc.web.page;

import lombok.Data;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withText;

@PageUrl("http://localhost:{port}/evaluation/")
@Data
public class EvaluationPage extends FluentPage {

    @FindBy(css = "#theme-2")
    private FluentWebElement buttonTheme;

    @FindBy(css = "#proposal-1")
    private FluentWebElement buttonProposal;

    @FindBy(css = "#termPaper-4")
    private FluentWebElement buttonTermPaperAvailableForEvaluationLastProfessor;

    @FindBy(css = "#termPaper-0")
    private FluentWebElement buttonTermPaperAvailableForEvaluationNotLastProfessor;

    @FindBy(css = "#termPaper-5")
    private FluentWebElement buttonTermPaperAvailableForEvaluationNotLastProfessorTwo;

    @FindBy(css = "#grade-4")
    private FluentWebElement gradeFinalLastProfessor;

    @FindBy(css = "#grade-status-4")
    private FluentWebElement gradeStatusLastProfessor;

    @FindBy(css = "#grade-5")
    private FluentWebElement gradeFinalLastProfessorTwo;

    @FindBy(css = "#grade-status-5")
    private FluentWebElement gradeStatusLastProfessorTwo;

    @FindBy(css = "#proposal-status-1")
    private FluentWebElement proposalStatusLastProfessor;

    @FindBy(css = "#grade-0")
    private FluentWebElement gradeFinalNotLastProfessor;

    @FindBy(css = "#grade-status-0")
    private FluentWebElement gradeStatusNotLastProfessor;

    @FindBy(css = "#termPaper-detail")
    private FluentWebElement termPaperDetail;

    @FindBy(css = "#form-termpaper-advisor")
    private FluentWebElement formEvaluationTermPaper;

    @FindBy(css = "#form-advice-evaluation")
    private FluentWebElement formEvaluationAdvice;

    @FindBy(css = "#buttonSubmitEvaluation")
    private FluentWebElement buttonSubmitEvaluation;

    @FindBy(css = "#buttonSubmitDraftEvaluation")
    private FluentWebElement buttonDraftSubmitEvaluation;


    @FindBy(css = "#evaluationSubmitConfirmationModal")
    private FluentWebElement evaluationSubmitConfirmationModal;

    @FindBy(css = "#yesSubmit")
    private FluentWebElement buttonYesSubmitModal;

    @FindBy(css = "#table-list-evaluate_length")
    private FluentWebElement tableListEvaluation;

    @FindBy(css = "#appraiser-name")
    private FluentWebElement appraiserName;

    @FindBy(css = "#considerations")
    private FluentWebElement considerations;

    @FindBy(css = "#final-grade-by-professor")
    private FluentWebElement finalGradeByProfessor;

    @FindBy(css = "#date-and-hour")
    private FluentWebElement dateAndHour;

    @FindBy(css = "#status3")
    private FluentWebElement buttonRadioRedo;


    public void isAt() {
        assertThat(window().title()).isEqualTo("Avaliação de Trabalhos");
    }

    public void isAtTermPaperEvaluation() {
        assertThat(window().title()).isEqualTo("Avaliação de Monografia");
    }

    public void isAtProposalEvaluation() {
        assertThat(window().title()).isEqualTo("Avaliação de Proposta");
    }

    public EvaluationPage selectTermPaperForEvaluationLastProfessor() {
        buttonTermPaperAvailableForEvaluationLastProfessor.click();
        return this;

    }

    public EvaluationPage selectTermPaperForEvaluationLastProfessorTwo() {
        buttonTermPaperAvailableForEvaluationNotLastProfessorTwo.click();
        return this;

    }

    public EvaluationPage selectTermPaperForEvaluationNotLastProfessor() {
        buttonTermPaperAvailableForEvaluationNotLastProfessor.click();
        return this;

    }

    public EvaluationPage selectRadioEvaluateRedoAndSubmit() {
        buttonRadioRedo.click();
        buttonSubmitEvaluation.click();
        return this;

    }

    public EvaluationPage selectProposalForEvaluationLastProfessor() {
        buttonProposal.click();
        return this;

    }
    public EvaluationPage awaitUntilTermPaperAppear() {
        await().atMost(5, TimeUnit.SECONDS).until(termPaperDetail).present();
        return this;
    }

    public EvaluationPage awaitUntilFormEvaluationTermPaperAppear() {
        await().atMost(5, TimeUnit.SECONDS).until(formEvaluationTermPaper).present();
        return this;
    }

    public EvaluationPage awaitUntilFormEvaluationAdviceAppear() {
        await().atMost(5, TimeUnit.SECONDS).until(formEvaluationAdvice).present();
        return this;
    }
    public EvaluationPage awaitUntilTableListEvaluateAppear() {
        await().atMost(5, TimeUnit.SECONDS).until(tableListEvaluation).present();
        return this;
    }


    public EvaluationPage awaitUntilAppraiserNameAppear() {
        await().atMost(5, TimeUnit.SECONDS).until(appraiserName).text().startsWith("orientador");
        return this;
    }


    public EvaluationPage awaitTwoSeconds() {
        await().atMost(2, TimeUnit.SECONDS).until(considerations).present();
        await().atMost(5, TimeUnit.SECONDS).until($("textarea")).size(1);
        return this;
    }

    public EvaluationPage fillAndSubmitForm(String... paramsOrdered) {
        $("input").fill().with(paramsOrdered);
        buttonSubmitEvaluation.click();
        return this;
    }

    public EvaluationPage fillAndSubmitAdviceForm(String... paramsOrdered) {
        $("input").fill().with(paramsOrdered);
        buttonSubmitEvaluation.click();
        return this;
    }

    public EvaluationPage fillAndSubmitDraftForm(String... paramsOrdered) {
        $("input").fill().with(paramsOrdered);
        buttonDraftSubmitEvaluation.click();
        return this;
    }

    public EvaluationPage awaitUntilConfirmationModal() {
        await().atMost(5, TimeUnit.SECONDS).until(evaluationSubmitConfirmationModal).present();
        return this;
    }

    public String getFileAbsolutePath(){
        File file = Paths.get(".", "src","main","resources","static","photos","face.jpg").normalize().toFile();

        return file.getAbsolutePath();
    }

    public EvaluationPage selectConfirmSubmit() {
        buttonYesSubmitModal.click();
        return this;

    }

    public void fillTextAreaForm(String... paramsOrdered) {
        $("textarea").fill().with(paramsOrdered);
    }
    /*


    public EvaluationPage openResult() {
        firstResult.click();
        return this;
    }

    public EvaluationPage downloadFile() {
        downloadFile.click();
        return this;
    }



    public EvaluationPage awaitUntilResultsOpen() {
        await().atMost(5, TimeUnit.SECONDS).until(firstResultDetails).present();
        return this;
    }*/

}