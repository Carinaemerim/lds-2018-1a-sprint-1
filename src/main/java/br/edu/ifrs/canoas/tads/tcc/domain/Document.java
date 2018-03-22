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
    /**
     * Logic to get final status from all evaluations
     */
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
        if (this.evaluations == null)
            return null;
        else {
            Double sumGrades;
            switch (this.documentType) {
                case THEME:
                        return status.APPROVED;
                case PROPOSAL:
                    break;
                case MONOGRAPH:

                    break;
                case TERMPAPER:
                    sumGrades = 0.0;
                    for (Evaluation eval : this.evaluations) {
                        if (eval instanceof Grade) {
                            sumGrades += ((Grade) eval).getFinalGrade();
                        }
                    }
                    if ((sumGrades / this.evaluations.size()) >= 6) {
                        return EvaluationStatus.APPROVED;
                    } else {
                        return EvaluationStatus.DISAPPROVED;
                    }

            }
        }

        return null;
    }


}