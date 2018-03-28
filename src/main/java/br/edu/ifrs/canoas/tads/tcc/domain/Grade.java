package br.edu.ifrs.canoas.tads.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Grade extends Evaluation {

	@Min(0)
	@Max(10)
	private Double literatureReview;
	@Min(0)
	@Max(10)
	private Double objective;
	@Min(0)
	@Max(10)
	private Double methodology;
	@Min(0)
	@Max(10)
	private Double theoreticalApproach;
	@Min(0)
	@Max(10)
	private Double experiencedSolution;
	@Min(0)
	@Max(10)
	private Double conclusion;

	@Min(0)
	@Max(10)
	private Double presentation;
	@Min(0)
	@Max(10)
	private Double closingExpectedTime;
	@Min(0)
	@Max(10)
	private Double adequacyOfPresentation;
	@Min(0)
	@Max(10)
	private Double subjectDomain;
	@Min(0)
	@Max(10)
	private Double developmentInLogicalSequence;
	@Min(0)
	@Max(10)
	private Double vocabulary;


	@Transient
	private Double finalGrade;

	public Double getFinalGrade() {

		return (getLiteratureReview() + getObjective() + getMethodology() + getTheoreticalApproach()
				+ getExperiencedSolution() + getConclusion() + getPresentation() + getClosingExpectedTime()
				+ getAdequacyOfPresentation() + getSubjectDomain() + getDevelopmentInLogicalSequence()
				+ getVocabulary()) / 12.0;
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
