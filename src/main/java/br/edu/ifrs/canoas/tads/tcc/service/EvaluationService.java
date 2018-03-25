package br.edu.ifrs.canoas.tads.tcc.service;

import br.edu.ifrs.canoas.tads.tcc.domain.*;
import br.edu.ifrs.canoas.tads.tcc.repository.*;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * Created by cassiano on 3/21/18.
 */
@Service
@AllArgsConstructor
public class EvaluationService {

    DocumentRepository documentRepository;
    TermPaperRepository termPaperRepository;
    EvaluationRepository evaluationRepository;
    GradeRepository gradeRepository;
    AdviceRepository adviceRepository;

    public Evaluation getOneEvaluationById(Long id) {
        return evaluationRepository.getOne(id);
    }
    public Grade getOneGradeById(Long id) {
        return gradeRepository.getOne(id);
    }

    public Grade getOneGrade(Document document, User professor) {
        return gradeRepository.findLastByDocumentIdAndAppraiserId(document.getId(), professor.getId());
    }

    public Evaluation getOneEvaluation(Document document, User professor) {
        return evaluationRepository.findLastByDocumentIdAndAppraiserId(document.getId(), 101L   ); // professor.getId()
    }

    public Evaluation getOne(Evaluation evaluation) {
        if (evaluation == null || evaluation.getId() == null)
            return null;
        Optional<Evaluation> optionalEvaluation = evaluationRepository.findById(evaluation.getId());
        return optionalEvaluation.isPresent() ? optionalEvaluation.get() : null;
    }

    public Grade getOne(Grade grade) {
        if (grade == null || grade.getId() == null)
            return null;
        Optional<Grade> optionalGrade = gradeRepository.findById(grade.getId());
        return optionalGrade.isPresent() ? optionalGrade.get() : null;
    }

    public Advice getOne(Advice advice) {
        if (advice == null || advice.getId() == null)
            return null;
        Optional<Advice> optionalAdivce = adviceRepository.findById(advice.getId());
        return optionalAdivce.isPresent() ? optionalAdivce.get() : null;
    }


    public Iterable<TermPaper> getTermPaperEvaluation(User user) {
        final List<TermPaper> listWithDuplicates =  user != null ?
                termPaperRepository.getTermPaperForEvaluation(user.getId()) : new ArrayList();

        if(listWithDuplicates != null) {
            List<TermPaper> listWithoutDuplicates =  listWithDuplicates.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(TermPaper::getId))),
                    ArrayList::new));
            return  listWithoutDuplicates;
        }
        return listWithDuplicates;
    }



    @Transactional
    public Advice saveThemeEvaluationDraft(Advice advice) {
        Advice fetchedAdvice = (Advice)this.getOne(advice);
        if (fetchedAdvice == null || fetchedAdvice.getId() == null)
            fetchedAdvice = new Advice();
        fetchedAdvice.setConsiderations(advice.getConsiderations());
        fetchedAdvice.setStatus(advice.getStatus());
        return adviceRepository.save(advice);
    }


    @Transactional
    public Advice saveThemeEvaluationFinal(Advice advice) {
        Advice fetchedAdvice = (Advice)this.getOne(advice);
        if (fetchedAdvice == null || fetchedAdvice.getId() == null)
            fetchedAdvice = new Advice();
        fetchedAdvice.setConsiderations(advice.getConsiderations());
        fetchedAdvice.setStatus(advice.getStatus());
        fetchedAdvice.setIsFinal(true);
        fetchedAdvice.setAppraiser(advice.getAppraiser());
        fetchedAdvice.setDocument(advice.getDocument());
       // fetchedAdvice = adviceRepository.save(fetchedAdvice);
        return getOne(adviceRepository.save(fetchedAdvice));
    }
}
