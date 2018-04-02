package br.edu.ifrs.canoas.tads.tcc.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import br.edu.ifrs.canoas.tads.tcc.domain.File;
import br.edu.ifrs.canoas.tads.tcc.domain.Message;
import br.edu.ifrs.canoas.tads.tcc.domain.User;
import br.edu.ifrs.canoas.tads.tcc.repository.FileRepository;
import br.edu.ifrs.canoas.tads.tcc.repository.MessageRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessageService {

	private final MessageRepository messageRepository;
	private final FileService fileService;

	public List<Message> findAllByReceiver(User receiver){

		return messageRepository.findAllByReceiver(receiver);
	}

	public List<Message> findAllBySender(User sender){

		return messageRepository.findAllBySender(sender);
	}


	public List<Message> queryFirst10ByViewedAndReceiverOrderByDateDesc(Boolean viewed, User user){

		return messageRepository.queryFirst10ByViewedAndReceiverOrderByDateDesc(viewed, user);
	}


	public List<Message> findAllByReceiverAndOrderByDateAsc(User receiver, Date date){

		return messageRepository.findAllByReceiverAndDateOrderByDateDesc(receiver, date);
	}

	public List<Message> findAllBySenderAndOrderByDateAsc(User sender, Date date){

		return messageRepository.findAllBySenderAndDateOrderByDateDesc(sender, date);
	}

	public List<Message> findAllBySenderOrReceiverOrderByDate(User sender){
		return messageRepository.findAllBySenderOrReceiverOrderByDate(sender, sender);
	}

	public Message save(Message message, MultipartFile mFile) throws IOException{

		if(mFile != null && mFile.getBytes().length > 0) {

			fileService.saveMultipartFile(mFile);
		}

		return messageRepository.save(message);
	}
}
