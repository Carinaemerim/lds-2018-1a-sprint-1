package br.edu.ifrs.canoas.tads.tcc.domain;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Grade extends Evaluation {

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
