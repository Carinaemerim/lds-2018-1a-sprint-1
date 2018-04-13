package br.edu.ifrs.canoas.tads.tcc.web.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.fluentlenium.core.annotation.Page;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.edu.ifrs.canoas.tads.tcc.web.config.MyFluentTest;
import br.edu.ifrs.canoas.tads.tcc.web.page.ThemePage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ThemeTest extends MyFluentTest {

	@Page
	private ThemePage themePage;

	// TODO nicolas.w CdT005 - RNG021
	@Test
	public void test1_checkThemeEmptyMessage() {
		// Given
		loginUserWithoutSubmittedTheme();
		themePage.go(port);
		themePage.isAt();
		assertThat(themePage.getSubmitForEvaluationButton().getElement().isEnabled()).isTrue();

		// When
		themePage.fillForm("Título Teste 1", "", 1, "Descrição de teste 1").submitForEvaluation().confirmSubmission()
				.awaitUntilErrorAppear();

		// Then
		assertThat(themePage.getThemeErr().getElement().getText()).isEqualTo("Não pode estar em branco");
		assertThat(themePage.getThemeInput().getElement().getAttribute("readonly")).isNotEqualTo("true");
		assertThat(themePage.getSubmitForEvaluationButton().getElement().isEnabled()).isTrue();
		assertThat(themePage.getSuccessMessage().present()).isFalse();
	}

	// TODO nicolas.w CdT005 - RNG022
	@Test
	public void test2_checkThemeStringOverflow() {
		// Given
		loginUserWithoutSubmittedTheme();
		themePage.go(port);
		themePage.isAt();
		assertThat(themePage.getSubmitForEvaluationButton().getElement().isEnabled()).isTrue();

		// When
		themePage.fillForm("Título Teste 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit", 1,
				"Descrição de teste 1").submitForEvaluation().confirmSubmission().awaitUntilErrorAppear();

		// Then
		assertThat(themePage.getThemeErr().getElement().getText()).isEqualTo("tamanho deve estar entre 0 e 50");
		assertThat(themePage.getThemeInput().getElement().getAttribute("readonly")).isNotEqualTo("true");
		assertThat(themePage.getSubmitForEvaluationButton().getElement().isEnabled()).isTrue();
		assertThat(themePage.getSuccessMessage().present()).isFalse();
	}

	// TODO nicolas.w CdT005 - RNG023
	@Test
	public void test3_checkStatusAfterExpirationTime() {
		// Given
		loginUserInEvaluationAfterExpirationTime();

		// When
		themePage.go(port);
		themePage.isAt();

		// Then
		assertThat(themePage.getStatusText().getElement().isDisplayed()).isTrue();
		assertThat(themePage.getAdvisorConsiderations().getElement().isDisplayed()).isTrue();
		assertThat(themePage.getStatusText().getElement().getText()).isEqualTo("REPROVADO");
		assertThat(themePage.getAdvisorConsiderations().getElement().getText())
				.isEqualTo("Tema reprovado automaticamente por inatividade do orientador selecionado.");
		assertThat(themePage.getThemeInput().getElement().getAttribute("readonly")).isNotEqualTo("true");
	}

	// TODO nicolas.w CdT005 - RNG038
	@Test
	public void test4_checkModalMessagesOnSubmit() {
		// Given
		loginUserWithoutSubmittedTheme();
		themePage.go(port);
		themePage.isAt();
		assertThat(themePage.getSubmitForEvaluationButton().getElement().isEnabled()).isTrue();

		// When
		themePage.fillForm("Título Teste 1", "Tema de teste", 1, "Descrição de teste 1").submitForEvaluation();

		// Then
		assertThat(themePage.getModalTitle().getElement().isDisplayed()).isTrue();
		assertThat(themePage.getModalText().getElement().isDisplayed()).isTrue();
		assertThat(themePage.getModalTitle().getElement().getText()).isEqualTo("Você tem certeza?");
		assertThat(themePage.getModalText().getElement().getText()).isEqualTo(
				"Após o envio não será mais possível alterar as informações até que o orientador selecionado avalie o seu tema");
	}

	// TODO nicolas.w CdT005 - RNG037
	@Test
	public void test5_checkStatusAfterSubmit() {
		// Given
		loginUserWithoutSubmittedTheme();
		themePage.go(port);
		themePage.isAt();
		assertThat(themePage.getSubmitForEvaluationButton().getElement().isEnabled()).isTrue();

		// When
		themePage.fillForm("Título Teste 1", "Tema de teste", 1, "Descrição de teste 1").submitForEvaluation()
				.confirmSubmission().awaitUntilResultsAppear();

		// Then
		assertThat(themePage.getSuccessMessage().present()).isTrue();
		assertThat(themePage.getSuccessMessage().getElement().getText()).isEqualToIgnoringCase("Formulário enviado com sucesso para avaliação");
		assertThat(themePage.getStatusText().getElement().isDisplayed()).isTrue();
		assertThat(themePage.getStatusText().getElement().getText()).isEqualTo("EM AVALIAÇÃO");
		assertThat(themePage.getThemeInput().getElement().getAttribute("readonly")).isEqualTo("true");
		assertThat(themePage.getAdvisorSelect().getElement().isEnabled()).isFalse();
	}
}