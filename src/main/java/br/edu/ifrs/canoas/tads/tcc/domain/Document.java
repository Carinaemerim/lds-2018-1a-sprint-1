package br.edu.ifrs.canoas.tads.tcc.domain;

import br.edu.ifrs.canoas.tads.tcc.config.auth.UserImpl;
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
    public Boolean getBank() {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long currentPrincipalId = ((UserImpl) authentication.getPrincipal()).getUser().getId();

        for (Professor professor : evaluationBoard.getProfessors())
            if ((professor.getId()).equals(currentPrincipalId))
                return true;

        return false;
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
                            //System.out.println("Acho o professor");
                            if (((Grade) eval).getIsFinal() == null || (!((Grade) eval).getIsFinal()))
                                return EvaluationStatus.EVALUATE;
                            else {
                                if (isAllEvaluated()) {
                                    return getStatus();
                                } else {
                                    // System.out.println("Ele não acho a avaliação do professor");
                                    return EvaluationStatus.IN_PROGRESS;
                                }

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
                                if (isAllEvaluated())
                                    return getStatus();
                                else
                                    return EvaluationStatus.IN_PROGRESS;

                            }
                        }
                    }
                }
            }
        }

        if (getBank()) {
            //System.out.println("É professor");
            return EvaluationStatus.EVALUATE;
        } else
            return getStatus();
    }


    private Boolean isAllEvaluated() {

        //TODO calcular quantidade avaliadores da banca,
        // se o nº de avaliadores for maior que o nº de avaliações retornar falso
        //System.out.println( "PROF" + (evaluationBoard.getProfessors()).size() );

        int counter = 0;
        for (Evaluation eval : evaluations) {
            if (documentType.equals(DocumentType.PROPOSAL)) {
                if (eval instanceof Advice)
                    if (eval.getIsFinal() != null && eval.getIsFinal())
                        counter++;
            }
            if (documentType.equals(DocumentType.TERMPAPER)) {
                if (eval instanceof Grade) {
                    if (eval.getIsFinal() != null && eval.getIsFinal())
                        counter++;
                }
            }
            if (documentType.equals(DocumentType.THEME)) {
                if (eval instanceof Advice) {
                    if (eval.getIsFinal() != null && eval.getIsFinal())
                        counter++;
                }
            }
        }
        if (counter < (evaluationBoard.getProfessors()).size()) {
            return false;
        } else {
            return true;
        }

       /* for (Evaluation eval : evaluations) {
            if (type.equals(DocumentType.TERMPAPER)) {
                if (eval instanceof Grade) {
                    if (((Grade) eval).getIsFinal() == null || (!((Grade) eval).getIsFinal())) {
                        return false;
                    }
                }

            }
            if (type.equals(DocumentType.PROPOSAL)) {
                if (eval instanceof Advice) {
                    if (((Advice) eval).getIsFinal() == null || (!((Advice) eval).getIsFinal())) {
                        return false;
                    }
                }
            }*/


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
    public Double getCalculatedGradeByProfessor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long currentPrincipalId = ((UserImpl) authentication.getPrincipal()).getUser().getId();
        Double sumGrades = 0.0;
        if (this.documentType.equals(DocumentType.TERMPAPER)) {

            if (this.documentType.equals(DocumentType.TERMPAPER)) {
                if (evaluations.size() > 0)
                    for (Evaluation eval : evaluations) {
                        if (eval instanceof Grade) {
                            if (((((Grade) eval).getAppraiser()).getId()).equals(currentPrincipalId))
                                sumGrades += ((Grade) eval).getFinalGrade();
                        }
                    }

            }
            return sumGrades; //TODO Colcoar null
        }
        return sumGrades;
    }


    @Transient
    public Double getFinalGrade() {

        Double sumGrades = 0.0;
        for (Evaluation eval : evaluations) {
            if (eval instanceof Grade) {
                if (evaluations.size() > 0)
                    sumGrades += ((Grade) eval).getFinalGrade();
            }
        }
        if (evaluations.size() != 0)
            return sumGrades / evaluations.size();

        return null;
    }

}


