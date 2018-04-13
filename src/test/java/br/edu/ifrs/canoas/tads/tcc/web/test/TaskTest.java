package br.edu.ifrs.canoas.tads.tcc.web.test;

import br.edu.ifrs.canoas.tads.tcc.web.config.MyFluentTest;
import br.edu.ifrs.canoas.tads.tcc.web.page.TaskPage;

import static org.assertj.core.api.Assertions.assertThat;

import org.fluentlenium.core.annotation.Page;
import org.junit.Before;
import org.junit.Test;

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
        taskPage.checkAndFillTitulo();
        taskPage.checkAndFillTarefa();
        taskPage.checkAndFillDataLimite();
        taskPage.checkAndFillAvaliacao();
        try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }
}
