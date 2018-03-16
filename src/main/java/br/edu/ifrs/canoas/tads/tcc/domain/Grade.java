package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Grade extends Evaluation{

    @Id
    @GeneratedValue
    private Long id;

    private Double literatureReview;
    private Double objective;
    private Double methodology;
    private Double theoreticalApproach;
    private Double experiencedSolution;
    private Double conclusion;
    private Double presentation;
    private Double closingExpectedTime;
    private Double adequacyOfPresentation;
    private Double subjectDomain;
    private Double developmentInLogicalSequence;
    private Double vocabulary;

}
