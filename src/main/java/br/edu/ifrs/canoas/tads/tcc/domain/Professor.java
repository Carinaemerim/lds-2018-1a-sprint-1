package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by rodrigo on 2/21/17.
 */
@Data
@Entity
public class Professor extends User{

    private String lattes;
    private String experience;
    private String skill;
    @ManyToMany
    private List<EvaluationBoard> board;

}