package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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

	private boolean notification;

	private CommonsMultipartFile file;
}
