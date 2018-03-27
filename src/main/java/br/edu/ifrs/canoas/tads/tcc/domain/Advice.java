package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Advice extends Evaluation {

    @NotNull
    private EvaluationStatus status;
}
