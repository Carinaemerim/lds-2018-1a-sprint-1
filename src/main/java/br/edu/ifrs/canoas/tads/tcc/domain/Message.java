package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Message {

	@ManyToOne
	private User sender;

	@ManyToOne
	private User receiver;

	@Id @GeneratedValue
	private Long id;

	private boolean viewed;

	private Date date;

	private String content;

}
