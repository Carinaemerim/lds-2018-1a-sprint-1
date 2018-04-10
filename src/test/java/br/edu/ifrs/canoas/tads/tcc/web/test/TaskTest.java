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
        super.loginUser();
    }

    @Test
    public void checkSearchForBoot() {
        //Given
    	taskPage.goUrl();
    	taskPage.isAt();

        //When
    	//taskPage.fillAndSubmitForm("boot").awaitLoadPeriodAppear();
        //and
    	//taskPage.openResult();
        //and
    	//taskPage.downloadFile();


        //Then
        assertThat(window().title()).isEqualTo("Cronograma");
       // assertThat(taskPage.getFirstResultDetails().displayed()).isTrue();
       // assertThat(taskPage.getFirstResultDetails().text()).containsIgnoringCase("Assim como o título, o resumo");
    }
}
