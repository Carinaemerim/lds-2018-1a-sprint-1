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
    
    @FindBy(xpath = "//*[@type = 'search']")
    private FluentWebElement search;
    
    @FindBy(xpath = "//*[@id = 'description']")
    private FluentWebElement inputDescription;
    
    @FindBy(xpath = "//*[@id = 'deadline']")
    private FluentWebElement inputdeadline;
    
    @FindBy(xpath = "//*[@id = 'NOTAPPLICABLE']")
    private FluentWebElement naoAvaliativo;

    @FindBy(xpath = "//*[text() = 'Delete']")
	private FluentWebElement delete;
    
    @FindBy(xpath = "//*[text() = 'Edit']")
	private FluentWebElement edit;
    
    @FindBy(xpath = "//button[@class='btn glyphicon glyphicon-menu-right']")
    private FluentWebElement btnRight;
    
    @FindBy(xpath = "//button[@class='btn glyphicon glyphicon-menu-left']")
    private FluentWebElement btnLeft;
    
	@FindBy(xpath = "//*[text() = '2018/01']")
	private FluentWebElement period201801;
	 
	@FindBy(xpath = "//*[text() = '2018/02']")
	private FluentWebElement period201802;
	 
	@FindBy(xpath = "//*[text() = '2017/02']")
	private FluentWebElement period201702;
    
	 @FindBy(xpath = "//*[text() = 'No data available in table']")
	    private FluentWebElement noData;


    public void goUrl() {
    	if(btnCronograma.clickable())
		btnCronograma.click();
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
    
    public void checkAndFillTitulo(String value) {
    	await().atMost(10, TimeUnit.SECONDS).until(inputTitle).present();
    	assertThat(inputTitle.displayed());
    	inputTitle.fill().with(value);
    }

	public void clickSalvar() {
		if(btnSalvar.clickable())
			btnSalvar.click();
		
	}

	public void checkAndFillTarefa(String value) {
		await().atMost(10, TimeUnit.SECONDS).until(inputDescription).present();
    	assertThat(inputDescription.displayed());
    	inputDescription.fill().with(value);
		
	}

	public void checkAndFillDataLimite(String value) {
		await().atMost(10, TimeUnit.SECONDS).until(inputdeadline).present();
    	assertThat(inputdeadline.displayed());
    	inputdeadline.fill().with(value);
		
	}

	public void checkAndFillAvaliacao() {
		await().atMost(10, TimeUnit.SECONDS).until(naoAvaliativo).present();
    	assertThat(naoAvaliativo.displayed());
    	naoAvaliativo.click();
		
	}

	public void serachTitulo(String value) {
		await().atMost(10, TimeUnit.SECONDS).until(search).present();
    	assertThat(search.displayed());
    	search.fill().with(value);
		
	}

	public void clickDelete() {
		await().atMost(10, TimeUnit.SECONDS).until(delete).present();
    	assertThat(delete.displayed());
    	delete.click();
		
	}

	public void clickEdit() {
		await().atMost(10, TimeUnit.SECONDS).until(edit).present();
    	assertThat(edit.displayed());
    	edit.click();
		
	}
	
	public void clickRight() {
		await().atMost(10, TimeUnit.SECONDS).until(btnRight).present();
    	assertThat(btnRight.displayed());
    	btnRight.click();
	}
	
	public void clickLeft() {
		await().atMost(10, TimeUnit.SECONDS).until(btnLeft).present();
    	assertThat(btnLeft.displayed());
    	btnLeft.click();
	}
	
	public void searchPeriod201801() {
		await().atMost(10, TimeUnit.SECONDS).until(period201801).present();
    	assertThat(period201801.displayed());
	}
	
	public void searchPeriod201802() {
		await().atMost(10, TimeUnit.SECONDS).until(period201802).present();
    	assertThat(period201802.displayed());
	}
	
	public void searchPeriod201702() {
		await().atMost(10, TimeUnit.SECONDS).until(period201702).present();
    	assertThat(period201702.displayed());
	}
	
	public void noDataInTheTable() {
		await().atMost(10, TimeUnit.SECONDS).until(noData).present();
    	assertThat(noData.displayed());
	}
}
	
