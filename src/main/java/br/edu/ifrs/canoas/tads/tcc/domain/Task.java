package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate deadline;
    @Enumerated(EnumType.ORDINAL)
    private TypeEvaluation typeEvaluation;

}

