package br.edu.ifrs.canoas.tads.tcc.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.tads.tcc.domain.Message;
import br.edu.ifrs.canoas.tads.tcc.domain.User;
import br.edu.ifrs.canoas.tads.tcc.repository.MessageRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessageService {

	private final MessageRepository messageRepository;

	public List<Message> findAllByReceiver(User receiver){

		return messageRepository.findAllByReceiver(receiver);
	}

	public List<Message> findAllBySender(User sender){

		return messageRepository.findAllBySender(sender);
	}


	public List<Message> queryFirst10ByViewed(Boolean viewed, Date date){

		return messageRepository.queryFirst10ByViewedAndDateOrderByDateDesc(viewed, date);
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
}
