package br.edu.ifrs.canoas.tads.tcc.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;

import br.edu.ifrs.canoas.tads.tcc.config.auth.UserImpl;
import lombok.Data;

@Entity
@Data
public class Document {

    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @ManyToOne
    private TermPaper termPaper;
    @Enumerated(EnumType.ORDINAL)
    private DocumentType documentType;
    private Boolean isFinal;
    @OneToOne
    private File file;
    @OneToMany(mappedBy = "document")
    private List<Evaluation> evaluations;

    private String title;


    @OneToOne
    private EvaluationBoard evaluationBoard;

    /**
     * Uma ideia de como deve ser o calculo do status geral do documento
     *
     * @return EvaluationStatus
     */
    private EvaluationStatus getStatusGeneral() {
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
    public Boolean getBank() {
        Long currentPrincipalId = getCurrentUserId();
        if (evaluationBoard == null)
            return false;
        for (Professor professor : evaluationBoard.getProfessors())
            if ((professor.getId()).equals(currentPrincipalId))
                return true;
        return false;
    }

    @Transient
    public EvaluationStatus getStatus() {

        // are you on the bench??
        if (getBank()) {
            if (evaluations == null || evaluations.size() == 0)
                return EvaluationStatus.EVALUATE;
            else {
                if (getAlreadyEvaluated()) {
                    if (isAllEvaluated()) {
                        return getStatusGeneral();
                    } else {
                        return EvaluationStatus.IN_PROGRESS;
                    }
                } else {
                    return EvaluationStatus.EVALUATE;
                }
            }
        } else {
            if (evaluations == null || evaluations.size() == 0) {
                return EvaluationStatus.IN_PROGRESS;
            } else {
                if (isAllEvaluated())
                    return getStatusGeneral();
                else
                    return EvaluationStatus.IN_PROGRESS;
            }
        }
    }

    @Transient
    public String getAllConsiderations() {
    	StringBuilder builder = new StringBuilder();
    	if (!CollectionUtils.isEmpty(evaluations)) {
    		 for (Evaluation eval : evaluations) {
    			 builder.append(eval.getConsiderations());
    			 builder.append("\n");
    		 }
    	}
    	return builder.toString();
    }

    private Boolean getAlreadyEvaluated() {

        Long currentPrincipalId = getCurrentUserId();

        for (Evaluation eval : evaluations) {
            if (documentType.equals(DocumentType.TERMPAPER)) {
                if (((((Grade) eval).getAppraiser()).getId()).equals(currentPrincipalId)) {
                    if (((Grade) eval).getIsFinal() != null && (((Grade) eval).getIsFinal())) {
                        return true;
                    }
                }
            } else {
                if (((((Advice) eval).getAppraiser()).getId()).equals(currentPrincipalId)) {
                    if (((Advice) eval).getIsFinal() != null && (((Advice) eval).getIsFinal())) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserImpl) authentication.getPrincipal()).getUser().getId();
    }


    private Boolean isAllEvaluated() {
        if (evaluationBoard == null)
            return false;
        int counter = 0;
        switch (documentType) {
            case THEME:
            case PROPOSAL:
                for (Evaluation eval : evaluations) {
                    if (eval instanceof Advice) {
                        if (eval.getIsFinal() != null && eval.getIsFinal() && ((Advice) eval).getStatus() != null)
                            counter++;
                    }
                }
                break;
            case TERMPAPER:
                for (Evaluation eval : evaluations) {
                    if (eval instanceof Grade) {
                        if (eval.getIsFinal() != null && eval.getIsFinal())
                            counter++;
                    }
                }
                break;
        }
        if (counter < (evaluationBoard.getProfessors()).size()) {
            return false;
        } else {
            return true;
        }

    }

    @Transient
    public String getColorStatus() {

        EvaluationStatus status = getStatus();
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
                return "fill-navy";
            default:
                return "fill-primary";
        }
    }

    @Transient
    public Double getCalculatedGradeByProfessor() {

        Long currentPrincipalId = getCurrentUserId();
        Double sumGrades = 0.0;
        if (this.documentType.equals(DocumentType.TERMPAPER)) {
            if (evaluations != null) {
                if (evaluations.size() > 0) {
                    for (Evaluation eval : evaluations) {
                        if (eval instanceof Grade) {
                            if (((((Grade) eval).getAppraiser()).getId()).equals(currentPrincipalId))
                                sumGrades += ((Grade) eval).getFinalGrade();
                        }
                    }
                }
            }
        }
        return sumGrades;
    }


    @Transient
    public Double getFinalGrade() {

        if (evaluations == null || evaluations.size() == 0)
            return null;

        if (!(isAllEvaluated()))
            return null;

        Double sumGrades = 0.0;
        for (Evaluation eval : evaluations) {
            if (eval instanceof Grade) {
                if (evaluations.size() > 0)
                    sumGrades += ((Grade) eval).getFinalGrade();
            }
        }

        return sumGrades / evaluations.size();

    }

}


