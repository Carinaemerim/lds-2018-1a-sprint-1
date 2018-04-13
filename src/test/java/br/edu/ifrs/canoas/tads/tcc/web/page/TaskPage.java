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
    
    @FindBy(xpath = "//section[@class='content-header']//h1//span[contains(text(),'Cronograma')]")
    private FluentWebElement cronogramaHeader;
    
    @FindBy(xpath = "//*[text() = 'Nova Tarefa']")
    private FluentWebElement novaTarefa;
    
    @FindBy(xpath = "//*[text() = 'Salvar']")
    private FluentWebElement btnSalvar;
    
    @FindBy(xpath = "//*[text() = 'Titulo:']")
    private FluentWebElement tituloLabel;
    
    @FindBy(xpath = "//*[@id = 'title']")
    private FluentWebElement inputTitle;
    
    @FindBy(xpath = "//*[text() = 'Lista de Tarefas']")
    private FluentWebElement listaDeTarefasLabel;
    
    @FindBy(xpath = "//*[text() = 'Não pode estar em branco']")
    private FluentWebElement notBlank;
    
    @FindBy(xpath = "//*[@id = 'description']")
    private FluentWebElement inputDescription;
    
    @FindBy(xpath = "//*[@id = 'deadline']")
    private FluentWebElement inputdeadline;
    
    @FindBy(xpath = "//*[@id = 'NOTAPPLICABLE']")
    private FluentWebElement naoAvaliativo;
    


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
    
    public void clickNewTask() {
    	if(novaTarefa.clickable())
    		novaTarefa.click();
    }
    
    public void checkListaDeTarefas() {
    	 assertThat(listaDeTarefasLabel.displayed());
    }
    
    public void checkNotBlank() {
   	 assertThat(notBlank.displayed());
   }
    
    public void checkTituloLabel() {
    	await().atMost(10, TimeUnit.SECONDS).until(tituloLabel).present();
    	 assertThat(tituloLabel.displayed());
    }
    
    public void checkAndFillTitulo() {
    	await().atMost(10, TimeUnit.SECONDS).until(inputTitle).present();
    	assertThat(inputTitle.displayed());
    	inputTitle.fill().with("Entregar monografia");
    }

	public void clickSalvar() {
		if(btnSalvar.clickable())
			btnSalvar.click();
		
	}

	public void checkAndFillTarefa() {
		await().atMost(10, TimeUnit.SECONDS).until(inputDescription).present();
    	assertThat(inputDescription.displayed());
    	inputDescription.fill().with("entregar trabalho completo com todas as sessçoes");
		
	}

	public void checkAndFillDataLimite() {
		await().atMost(10, TimeUnit.SECONDS).until(inputdeadline).present();
    	assertThat(inputdeadline.displayed());
    	inputdeadline.fill().with("05/14/2018");
		
	}

	public void checkAndFillAvaliacao() {
		await().atMost(10, TimeUnit.SECONDS).until(naoAvaliativo).present();
    	assertThat(naoAvaliativo.displayed());
    	naoAvaliativo.click();
		
	}
}
	
