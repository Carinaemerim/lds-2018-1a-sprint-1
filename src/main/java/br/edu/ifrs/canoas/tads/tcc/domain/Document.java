package br.edu.ifrs.canoas.tads.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import lombok.Data;

@Entity
@Data
public class Document {


	@Id @GeneratedValue private Long id;
	private String type;



}
