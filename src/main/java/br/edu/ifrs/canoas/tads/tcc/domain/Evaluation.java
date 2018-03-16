package br.edu.ifrs.canoas.tads.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Inheritance
@Data
public abstract class Evaluation {

	@Id
	@GeneratedValue
	private Long id;
	private String considerations;
	@ManyToOne
	private Document document;
}
