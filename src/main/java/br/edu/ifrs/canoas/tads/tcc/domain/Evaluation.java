package br.edu.ifrs.canoas.tads.tcc.domain;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Evaluation {

	@Id @GeneratedValue private Long id;

	private String considerations;

	@OneToOne
	private File file;

	@Enumerated(EnumType.ORDINAL)
	private DocumentType documentType;
}
