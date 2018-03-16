package br.edu.ifrs.canoas.tads.tcc.domain;

import javax.persistence.*;


import lombok.Data;

@Entity
@Data
public class Document {


	@Id @GeneratedValue
	private Long id;
	@OneToOne File file;

	@ManyToOne
	private TermPaper termPaper;
	private Boolean isFinal;
	private String Status;

}
