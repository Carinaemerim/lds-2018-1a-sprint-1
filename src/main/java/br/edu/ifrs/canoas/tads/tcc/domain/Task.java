package br.edu.ifrs.canoas.tads.tcc.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class Task {

	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotBlank
	private Date deadline;
	@NotBlank
	private int typeEvaluation;
}
