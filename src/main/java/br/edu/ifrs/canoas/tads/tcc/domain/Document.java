package br.edu.ifrs.canoas.tads.tcc.domain;

import br.edu.ifrs.canoas.tads.tcc.config.auth.UserImpl;
import br.edu.ifrs.canoas.tads.tcc.service.EvaluationBoardService;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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


    private String title;


    @OneToOne
    private EvaluationBoard evaluationBoard;

    /**
     * Uma ideia de como deve ser o calculo do status do documento <- nicolas
     *
     * @return EvaluationStatus
     */
    @Transient
    public EvaluationStatus getStatus() {


        if (evaluations == null || evaluations.size() == 0) {
            return EvaluationStatus.EVALUATE;
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

    @Transient
    public EvaluationStatus getStatusByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long currentPrincipalId = ((UserImpl) authentication.getPrincipal()).getUser().getId();


        if (evaluations == null || evaluations.size() == 0) {
            return EvaluationStatus.EVALUATE;
        } else {
            if (this.documentType.equals(DocumentType.TERMPAPER)) {
                for (Evaluation eval : evaluations) {
                    if (eval instanceof Grade) {
                        if (((((Grade) eval).getAppraiser()).getId()).equals(currentPrincipalId)) {
                            if (((Grade) eval).getIsFinal() == null || (!((Grade) eval).getIsFinal()))
                                return EvaluationStatus.EVALUATE;
                            else {
                                if (isAllEvaluated(documentType)) {
                                    return getStatus();
                                } else
                                    return EvaluationStatus.IN_PROGRESS;

                            }
                        }
                    }
                }

            } else {
                for (Evaluation eval : evaluations) {
                    if (eval instanceof Advice) {
                        if (documentType.equals(DocumentType.THEME)) {
                            return getStatus();
                        }
                        if (((((Advice) eval).getAppraiser()).getId()).equals(currentPrincipalId)) {

                            if (((Advice) eval).getIsFinal() == null || (!((Advice) eval).getIsFinal()))
                                return EvaluationStatus.EVALUATE;
                            else {
                                if (isAllEvaluated(documentType))
                                    return getStatus();
                                else
                                    return EvaluationStatus.IN_PROGRESS;

                            }
                        }
                    }
                }
            }
        }

        return EvaluationStatus.EVALUATE;
    }

    private Boolean isAllEvaluated(DocumentType type) {

        //TODO colocar quantidade avaliadores da banca
        for (Evaluation eval : evaluations) {
            if (type.equals(type)) {
                if (eval instanceof Grade) {
                    if (((Grade) eval).getIsFinal() == null || (!((Grade) eval).getIsFinal())) {
                        return false;
                    }
                }
                if (eval instanceof Advice) {
                    if (((Advice) eval).getIsFinal() == null || (!((Advice) eval).getIsFinal())) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Transient
    public String getColorStatus() {


        EvaluationStatus status = getStatusByUser();
        if (status == null)
            return "btn-primary";


        switch (status) {
            case APPROVED:
                return "btn-success";
            case DISAPPROVED:
                return "btn-danger";
            case REDO:
                return "btn-warning";
            case IN_PROGRESS:
                return "bg-navy";
            default:
                return "btn-primary";
        }
    }

    @Transient
    public String getColorFillSvg() {
        EvaluationStatus status = getStatus();
        if (status == null)
            return "fill-primary";
        switch (status) {
            case APPROVED:
                return "fill-success";
            case DISAPPROVED:
                return "fill-danger";
            case REDO:
                return "fill-warning";
            case IN_PROGRESS:
                return "fill-primary";
            default:
                return "fill-primary";
        }
    }

    @Transient
    public Double getFinalGrade() {
        if (this.documentType.equals(DocumentType.TERMPAPER)) {
            Double sumGrades = 0.0;
            for (Evaluation eval : evaluations) {
                if (eval instanceof Grade) {
                    sumGrades += ((Grade) eval).getFinalGrade();
                }
            }
            return sumGrades / evaluations.size();
        }
        return 0.0; //TODO Colcoar null
    }


}