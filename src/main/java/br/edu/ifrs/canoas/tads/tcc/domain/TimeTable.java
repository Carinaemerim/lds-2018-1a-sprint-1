package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class TimeTable {

	
	@Id @GeneratedValue private Long id;
}
