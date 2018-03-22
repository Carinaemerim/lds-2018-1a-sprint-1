package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Document {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private TermPaper termPaper;
	@Enumerated(EnumType.ORDINAL)
	private DocumentType documentType;
	private Boolean isFinal;
	@OneToOne
	private File file;
	@OneToMany(mappedBy = "document")
	private List<Evaluation> evaluations;
	/** Logic to get final status from all evaluations */
	@Transient
	private EvaluationStatus status;

	private String title;


	@OneToOne
    private EvaluationBoard evaluationBoard;

	/**
	 * Uma ideia de como deve ser o calculo do status do documento <- nicolas
	 *
	 * @return EvaluationStatus
	 */
	public EvaluationStatus getStatus() {
		if (evaluations == null || evaluations.size() == 0) {
			return null;
		} else {
			if (this.documentType.equals(DocumentType.TERMPAPER)) {
				Double sumGrades = 0.0;
				for (Evaluation eval : evaluations) {
					if (eval instanceof Grade) {
						sumGrades += ((Grade) eval).getFinalGrade();
					}
				}
				if ((sumGrades / evaluations.size()) >= 6) {
					return EvaluationStatus.APPROVED;
				} else {
					return EvaluationStatus.DISAPPROVED;
				}
			} else {
				EvaluationStatus status = EvaluationStatus.APPROVED;
				for (Evaluation eval : evaluations) {
					if (eval instanceof Advice) {
						if (((Advice) eval).getStatus().equals(EvaluationStatus.DISAPPROVED)) {
							return EvaluationStatus.DISAPPROVED;
						} else if (((Advice) eval).getStatus().equals(EvaluationStatus.REDO)) {
							status = EvaluationStatus.REDO;
						}
					}
				}
				return status;
			}
		}
	}
}
