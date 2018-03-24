package br.edu.ifrs.canoas.tads.tcc.domain;

import javax.persistence.*;

import lombok.Data;

@Entity
@Inheritance
@Data
@DiscriminatorColumn(name="evaluation_type")
public abstract class Evaluation {

	@Id
	@GeneratedValue
	private Long id;
	private String considerations;
	@ManyToOne
	private Document document;
	@OneToOne
	private Professor appraiser;
}
