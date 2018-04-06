package br.edu.ifrs.canoas.tads.tcc.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageServiceTest {
	@Autowired
	MessageService service;
	
	public void shouldFindAllByReceiver() throws Exception {
		
	}
	
	public void shouldNotFindAllByNullReceiver() throws Exception {
		
	}
	
	public void shouldFindAllBySender() throws Exception {
		
	}
	
	public void shouldNotFindAllByNullSender() throws Exception {
		
	}
	
	public void shouldFindAllBySenderOrReceiverOrderByDate() throws Exception {
		
	}
	
	public void shouldNotFindAllByNullSenderOrReceiverOrderByDate() throws Exception {
		
	}
	
	public void shouldNotFindAllBySenderOrNullReceiverOrderByDate() throws Exception {
		
	}
	
	public void shouldNotFindAllByNullSenderOrNullReceiverOrderByDate() throws Exception {
		
	}
	
	public void shouldSaveWithouthAttachment() throws Exception {
		
	}
	
	public void shouldSaveWithAttachment() throws Exception {
		
	}
	
	public void shouldNotSave() throws Exception {
		
	}
}