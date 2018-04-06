package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"title"})})
public class AcademicYear {

	
	@Id @GeneratedValue private Long id;
	@NotBlank
	private String title;
}


