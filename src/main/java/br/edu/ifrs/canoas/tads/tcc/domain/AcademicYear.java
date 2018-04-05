package br.edu.ifrs.canoas.tads.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"title"})})
public class AcademicYear {

	
	@Id @GeneratedValue private Long id;
	@NotBlank
	private String title;
}


