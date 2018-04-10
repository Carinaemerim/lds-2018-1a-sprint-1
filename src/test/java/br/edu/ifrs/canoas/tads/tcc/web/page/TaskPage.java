package br.edu.ifrs.canoas.tads.tcc.web.page;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Sleeper;

import lombok.Data;

/**
 * Created by Mariele on 04/8/18.
 */

@PageUrl("http://localhost:{port}/schedule/index")
public class TaskPage extends FluentPage {

	@FindBy(css = "#submit-button")
    private FluentWebElement submitButton;

    @FindBy(xpath = "//*[text() = 'Cronograma']")
    private FluentWebElement btnCronograma;
    
    @FindBy(css = "#nav-period")
    private FluentWebElement navPeriod;

    @FindBy(xpath = "//*[text() = 2018/01")
	private FluentWebElement period;

    public void goUrl() {
    	if(btnCronograma.clickable())
		btnCronograma.click();
    }

    public void isAt() {
        try {
			Thread.sleep(5000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        assertThat(window().title()).isEqualTo("Cronograma");
    }

    public TaskPage fillAndSubmitForm(String... paramsOrdered) {
        $("input").fill().with(paramsOrdered);
        btnCronograma.click();
        return this;
    }
    
    public TaskPage awaitLoadPeriodAppear() {
		await().atMost(5, TimeUnit.SECONDS).until(period).present();
        return new TaskPage();
    }
}
	
