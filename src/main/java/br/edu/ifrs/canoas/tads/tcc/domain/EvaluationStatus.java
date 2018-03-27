package br.edu.ifrs.canoas.tads.tcc.domain;


public enum EvaluationStatus {

	APPROVED, DISAPPROVED, REDO, EVALUATE, IN_PROGRESS;

	public static final EvaluationStatus[] ALL = { APPROVED, DISAPPROVED, REDO };

}
