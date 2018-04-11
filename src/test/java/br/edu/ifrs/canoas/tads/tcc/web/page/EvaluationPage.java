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

    @FindBy(css = "#termPaper-4")
    private FluentWebElement buttonTermPaperAvailableForEvaluationLastProfessor;

    @FindBy(css = "#termPaper-0")
    private FluentWebElement buttonTermPaperAvailableForEvaluationNotLastProfessor;


    @FindBy(css = "#grade-4")
    private FluentWebElement gradeFinalLastProfessor;

    @FindBy(css = "#grade-status-4")
    private FluentWebElement gradeStatusLastProfessor;

    @FindBy(css = "#grade-0")
    private FluentWebElement gradeFinalNotLastProfessor;

    @FindBy(css = "#grade-status-0")
    private FluentWebElement gradeStatusNotLastProfessor;

    @FindBy(css = "#termPaper-detail")
    private FluentWebElement termPaperDetail;

    @FindBy(css = "#form-termpaper-advisor")
    private FluentWebElement formEvaluationTermPaper;

    @FindBy(css = "#buttonSubmitEvaluation")
    private FluentWebElement buttonSubmitEvaluation;

    @FindBy(css = "#buttonSubmitDraftEvaluation")
    private FluentWebElement buttonDraftSubmitEvaluation;


    @FindBy(css = "#gradeEvaluationSubmitConfirmationModal")
    private FluentWebElement gradeEvaluationSubmitConfirmationModal;

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

    @FindBy(css = "# date-and-hour")
    private FluentWebElement dateAndHour;




  /*  @FindBy(xpath = "//*[text() = Rascunho salvo com sucesso!")
    private FluentWebElement messageDraftSaved;*/

    public void isAt() {
        assertThat(window().title()).isEqualTo("Avaliação de Trabalhos");
    }

    public void isAtTermPaperEvaluation() {
        assertThat(window().title()).isEqualTo("Avaliação de Monografia");
    }

    public EvaluationPage selectTermPaperForEvaluationLastProfessor() {
        buttonTermPaperAvailableForEvaluationLastProfessor.click();
        return this;

    }
    public EvaluationPage selectTermPaperForEvaluationNotLastProfessor() {
        buttonTermPaperAvailableForEvaluationNotLastProfessor.click();
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
    public EvaluationPage awaitUntilTableListEvaluateAppear() {
        await().atMost(5, TimeUnit.SECONDS).until(tableListEvaluation).present();
        return this;
    }


    public EvaluationPage awaitUntilAppraiserNameAppear() {
        await().atMost(5, TimeUnit.SECONDS).until(appraiserName).text().startsWith("orientador");
        return this;
    }


    public EvaluationPage awaitFiveSeconds() {
        await().atMost(5, TimeUnit.SECONDS);
        return this;
    }

    public EvaluationPage fillAndSubmitForm(String... paramsOrdered) {
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
        await().atMost(5, TimeUnit.SECONDS).until(gradeEvaluationSubmitConfirmationModal).present();
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