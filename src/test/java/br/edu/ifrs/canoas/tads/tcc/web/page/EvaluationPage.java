package br.edu.ifrs.canoas.tads.tcc.web.page;

import lombok.Data;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@PageUrl("http://localhost:{port}/evaluation/")
@Data
public class EvaluationPage extends FluentPage {

    @FindBy(css = "#theme-2")
    private FluentWebElement buttonTheme;

    @FindBy(css = "#termPaper-4")
    private FluentWebElement buttonTermPaperAvailableForEvaluation;

    @FindBy(css = "#termPaper-detail")
    private FluentWebElement termPaperDetail;
   /* @FindBy(css = "#search-button")
    private FluentWebElement searchButton;*/


    public void isAt() {
        assertThat(window().title()).isEqualTo("Avaliação de Trabalhos");
    }

    public void isAtTermPaperEvaluation() {

        buttonTermPaperAvailableForEvaluation.click();
        assertThat(window().title()).isEqualTo("Avaliação de Monografia");
    }

    public EvaluationPage awaitUntilTermPaperAppear() {
        await().atMost(5, TimeUnit.SECONDS).until(termPaperDetail).present();
        return this;
    }

    /*
    public EvaluationPage fillAndSubmitForm(String... paramsOrdered) {
        $("input").fill().with(paramsOrdered);
        buttonTheme.click();
        return this;
    }


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