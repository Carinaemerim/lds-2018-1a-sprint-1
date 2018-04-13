package br.edu.ifrs.canoas.tads.tcc.web.test;

import org.fluentlenium.core.annotation.Page;
import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.tads.tcc.web.config.MyFluentTest;
import br.edu.ifrs.canoas.tads.tcc.web.page.TaskPage;

/**
 * Created by Mariele on 04/8/18.
 */
public class TaskTest extends MyFluentTest{
	
	@Page
    TaskPage taskPage;
	
	@Before
    public void loginUser(){
        super.loginProfessor();
    }
	
	@Test
    public void TabelaSemDados() {
        //Given
    	taskPage.goUrl();
    	taskPage.checkListaDeTarefas();;
        //Then
    	taskPage.searchPeriod201802();
    	taskPage.clickLeft();
    	taskPage.clickLeft();
		taskPage.searchPeriod201702();
    	taskPage.noDataInTheTable();
        
    }

    @Test
    public void CadastroNovaTarefaSemDados() {
        //Given
    	taskPage.goUrl();
    	taskPage.checkListaDeTarefas();;
        //Then
        taskPage.clickNewTask();
        taskPage.checkTituloLabel();
        taskPage.clickSalvar();
        taskPage.checkNotBlank();
        taskPage.checkTituloLabel();
        
    }
    
    @Test
    public void CadastroNovaTarefa() {
        //Given
    	taskPage.goUrl();
    	taskPage.checkListaDeTarefas();;
        //Then
        taskPage.clickNewTask();
        taskPage.checkAndFillTitulo("Entregar monografia");
        taskPage.checkAndFillTarefa("entregar trabalho completo com todas as sessçoes");
        taskPage.checkAndFillDataLimite("05/14/2018");
        taskPage.checkAndFillAvaliacao();
        taskPage.clickSalvar();
        taskPage.serachTitulo("Entregar monografia");
        taskPage.clickDelete();
        
    }
    
    @Test
    public void EditarNovaTarefa() {
        //Given
    	taskPage.goUrl();
    	taskPage.checkListaDeTarefas();;
        //Then
        taskPage.clickNewTask();
        taskPage.checkAndFillTitulo("Entregar monografia edit");
        taskPage.checkAndFillTarefa("entregar trabalho completo com todas as sessçoes edit");
        taskPage.checkAndFillDataLimite("05/14/2018");
        taskPage.checkAndFillAvaliacao();
        taskPage.clickSalvar();
        taskPage.serachTitulo("Entregar monografia edit");
        taskPage.clickEdit();
        taskPage.checkAndFillTitulo("Entregar monografia edit1");
        taskPage.clickSalvar();
        taskPage.serachTitulo("Entregar monografia edit1");
        taskPage.clickDelete();
    }
    
    @Test
    public void DeletarTarefa() {
        //Given
    	taskPage.goUrl();
    	taskPage.checkListaDeTarefas();;
        //Then
        taskPage.clickDelete();
    }
    
    @Test
    public void NavegarPeriodos() {
        //Given
    	taskPage.goUrl();
    	taskPage.checkListaDeTarefas();;
        //Then
    	taskPage.searchPeriod201801();
    	taskPage.clickLeft();
		taskPage.searchPeriod201702();
	    taskPage.clickRight();
	    taskPage.searchPeriod201801();
	    taskPage.clickRight();
    	taskPage.searchPeriod201802();
    	
    }
}
