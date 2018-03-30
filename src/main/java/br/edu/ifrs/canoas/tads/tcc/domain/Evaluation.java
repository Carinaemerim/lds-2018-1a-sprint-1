package br.edu.ifrs.canoas.tads.tcc.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Data;

import java.util.Date;

@Entity
@Inheritance
@Data
@DiscriminatorColumn(name="evaluation_type")
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"document_id" , "appraiser_id"})})
public abstract class Evaluation {

	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	private String considerations;

	@OneToOne
	private Document document;

	@OneToOne
	private Professor appraiser;

	private Boolean isFinal;

	private Date createdOn;

	@OneToOne
	private File file;
}
