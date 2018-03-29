package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
    @OneToMany(mappedBy = "advisor", fetch = FetchType.EAGER)
    private List<TermPaper> termPapers;
    @ManyToMany
    private List<EvaluationBoard> board;

}