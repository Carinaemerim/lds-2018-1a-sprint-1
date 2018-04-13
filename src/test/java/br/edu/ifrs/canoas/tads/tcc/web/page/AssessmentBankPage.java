package br.edu.ifrs.canoas.tads.tcc.web.page;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Data;
/**
 * Created by Karen on 04/6/18.
 */

@PageUrl("http://localhost:{port}/assessment-bank/")
@Data
public class AssessmentBankPage  extends FluentPage {

    @FindBy(css = "#submit-button")
    private FluentWebElement submitButton;

    public void isAt() {
        assertThat(window().title()).isEqualTo("Listagem de Professores para sele\\u00E7\\u00E3o");
    }
}
