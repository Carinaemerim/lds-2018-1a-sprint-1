package br.edu.ifrs.canoas.tads.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.Transient;

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

	@Transient
	private Double finalGrade;

	public Double getFinalGrade() {
		return (this.literatureReview + this.objective + this.methodology + this.theoreticalApproach
				+ this.experiencedSolution + this.conclusion + this.presentation + this.closingExpectedTime
				+ this.adequacyOfPresentation + this.subjectDomain + this.developmentInLogicalSequence
				+ this.vocabulary) / 12.0;
	}


	public Double getLiteratureReview() {

		return literatureReview != null ? literatureReview : 0;
	}

	public Double getObjective() {
		return objective != null ? objective : 0;
	}

	public Double getMethodology() {
		return methodology != null ? methodology : 0;
	}

	public Double getTheoreticalApproach() {
		return theoreticalApproach != null ? theoreticalApproach : 0;
	}

	public Double getExperiencedSolution() {
		return experiencedSolution != null ? experiencedSolution : 0;
	}

	public Double getConclusion() {
		return conclusion != null ? conclusion : 0;
	}

	public Double getPresentation() {
		return presentation != null ? presentation : 0;
	}

	public Double getClosingExpectedTime() {
		return closingExpectedTime != null ? closingExpectedTime : 0;
	}

	public Double getAdequacyOfPresentation() {
		return adequacyOfPresentation != null ? adequacyOfPresentation : 0;
	}

	public Double getSubjectDomain() {
		return subjectDomain != null ? subjectDomain : 0;
	}

	public Double getDevelopmentInLogicalSequence() {
		return developmentInLogicalSequence != null ? developmentInLogicalSequence : 0;
	}

	public Double getVocabulary() {
		return vocabulary != null ? vocabulary : 0;
	}

}
