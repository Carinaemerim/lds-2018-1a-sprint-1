package br.edu.ifrs.canoas.tads.tcc.service;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ifrs.canoas.tads.tcc.domain.Task;

/**
 * Created by Mariele on 3/03/18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class TaskServiceTest {

    @Autowired
    TaskService service;

    private final Long ID = 100L;

    @Test
    public void shouldNotHaveIdNull() throws Exception {
    	Task  task = new Task();
        	
        assertThat(task.getId()).isNull();
    }
    
    @Test
    public void shouldHaveId() throws Exception {
    	Task  task = new Task();   
    	task.setId(ID);
        assertThat(task.getId().equals(ID));
    }
}
