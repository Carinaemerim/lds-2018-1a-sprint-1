package br.edu.ifrs.canoas.tads.tcc.web.page;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.concurrent.TimeUnit;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@PageUrl("http://localhost:{port}/document/")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class ThemePage extends FluentPage {

	@FindBy(css = "#title")
	private FluentWebElement titleInput;

	@FindBy(css = "#theme")
	private FluentWebElement themeInput;

	@FindBy(css = "#advisor")
	private FluentWebElement advisorSelect;

	@FindBy(css = "#description")
	private FluentWebElement descriptionTextarea;

	@FindBy(css = "#save-draft")
	private FluentWebElement saveDraftButton;

	@FindBy(css = "#submit-for-evaluation")
	private FluentWebElement submitForEvaluationButton;

	@FindBy(css = "#submit-yes")
	private FluentWebElement submitYesButton;

	@FindBy(css = "#theme-err")
	private FluentWebElement themeErr;

	@FindBy(css = "#status-text")
	private FluentWebElement statusText;

	@FindBy(css = "#advisor-considerations")
	private FluentWebElement advisorConsiderations;

	@FindBy(css = "#submit-confirmation-title")
	private FluentWebElement modalTitle;

	@FindBy(css = "#submit-confirmation-text")
	private FluentWebElement modalText;

	public void isAt() {
		assertThat(window().title()).isEqualTo("Gerenciamento de Documentos");
	}

	//TODO CdT005 nicolas.w
	public ThemePage fillForm(String title, String theme, int advisorIdx, String description) {
		titleInput.fill().with(title);
		themeInput.fill().with(theme);
		advisorSelect.fillSelect().withIndex(advisorIdx);
		descriptionTextarea.fill().withText(description);
		return this;
	}

	//TODO CdT005 nicolas.w
	public ThemePage submitForEvaluation() {
		submitForEvaluationButton.click();
		return awaitUntilModalDialogAppear();
	}

	//TODO CdT005 nicolas.w
	public ThemePage confirmSubmission() {
		submitYesButton.click();
		return this;
	}

	//TODO CdT005 nicolas.w
	public ThemePage awaitUntilModalDialogAppear() {
		await().atMost(5, TimeUnit.SECONDS).until(modalTitle).displayed();
		await().atMost(5, TimeUnit.SECONDS).until(modalText).displayed();
		return this;
	}

	//TODO CdT005 nicolas.w
	public ThemePage awaitUntilErrorAppear() {
		await().atMost(5, TimeUnit.SECONDS).until(el(".help-block")).present();
		return this;
	}

	//TODO CdT005 nicolas.w
	public ThemePage awaitUntilResultsAppear() {
		await().atMost(5, TimeUnit.SECONDS).until(el(".callout-success.lead")).present();
		return this;
	}
}