package br.edu.ifrs.canoas.tads.tcc.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class Document {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private TermPaper termPaper;
	@Enumerated(EnumType.ORDINAL)
	private DocumentType documentType;
	private Boolean isFinal;
	@OneToOne
	private File file;
	@OneToMany(mappedBy = "document")
	private List<Evaluation> evaluations;
	/** Logic to get final status from all evaluations */
	@Transient
	private String status;
}
