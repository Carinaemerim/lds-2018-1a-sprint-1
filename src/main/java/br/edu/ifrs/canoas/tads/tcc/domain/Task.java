package br.edu.ifrs.canoas.tads.tcc.domain;

import java.time.LocalDate;
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
public class Task{ //implements Alert{

	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	private String period;
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate deadline;
	private int typeEvaluation;

}
