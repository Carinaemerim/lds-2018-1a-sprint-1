package br.edu.ifrs.canoas.tads.tcc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrs.canoas.tads.tcc.domain.Message;
import br.edu.ifrs.canoas.tads.tcc.domain.User;

public interface MessageRepository extends JpaRepository<Message, Long>{

	List<Message> findAllByReceiver(User receiver);
	
	List<Message> findAllBySenderOrByReceiverOrderByDate(User sender);

	List<Message> findAllBySender(User sender);

	List<Message> queryFirst10ByViewedAndDateOrderByDateDesc(Boolean viewed, Date date);

	List<Message> findAllByReceiverAndDateOrderByDateDesc(User receiver, Date date);

	List<Message> findAllBySenderAndDateOrderByDateDesc(User sender, Date date);

}
