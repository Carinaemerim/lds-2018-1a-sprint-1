package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class EvaluationBoard {

	@Id @GeneratedValue
    private Long id;
	@OneToOne
	private Document document;
	@ManyToMany
	private List<Professor> professors;

}
