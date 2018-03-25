package br.edu.ifrs.canoas.tads.tcc.domain;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Advice extends Evaluation {
	private EvaluationStatus status;
}
