package br.edu.ifrs.canoas.tads.tcc.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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
	@NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date deadline;
	private int typeEvaluation;
}
