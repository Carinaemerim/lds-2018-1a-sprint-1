package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Advice extends Evaluation {

    private EvaluationStatus status;
}
