package br.edu.ifrs.canoas.tads.tcc.web.page;

import lombok.Data;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@PageUrl("http://localhost:{port}/document/")
@Data
public class ThemePage extends FluentPage {

    @FindBy(css = "#criteria")
    private FluentWebElement criteria;

    @FindBy(css = "#search-button")
    private FluentWebElement searchButton;

    @FindBy(css = "#search-results")
    private FluentWebElement searchResults;

    @FindBy(css = "#result-0")
    private FluentWebElement firstResult;

    @FindBy(css = "#collapse-0")
    private FluentWebElement firstResultDetails;

    @FindBy(css = "#file-0")
    private FluentWebElement downloadFile;

    public void isAt() {
        assertThat(window().title()).isEqualTo("Documento");
    }

    public ThemePage fillAndSubmitForm(String... paramsOrdered) {
        $("input").fill().with(paramsOrdered);
        searchButton.click();
        return this;
    }

    public ThemePage openResult() {
        firstResult.click();
        return this;
    }

    public ThemePage downloadFile() {
        downloadFile.click();
        return this;
    }

    public ThemePage awaitUntilResultsAppear() {
        await().atMost(5, TimeUnit.SECONDS).until(searchResults).present();
        return this;
    }

    public ThemePage awaitUntilResultsOpen() {
        await().atMost(5, TimeUnit.SECONDS).until(firstResultDetails).present();
        return this;
    }

}