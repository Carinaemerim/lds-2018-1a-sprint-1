package br.edu.ifrs.canoas.tads.tcc.service;

import br.edu.ifrs.canoas.tads.tcc.domain.*;
import br.edu.ifrs.canoas.tads.tcc.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * Created by cassiano on 3/21/18.
 */
@Service
@AllArgsConstructor
public class EvaluationService {


    TermPaperRepository termPaperRepository;
    EvaluationRepository evaluationRepository;
    AdviceRepository adviceRepository;


    public Evaluation getOneEvaluation(Document document, User professor) {
        return evaluationRepository.findLastByDocumentIdAndAppraiserId(document.getId(), professor.getId());
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
    public Advice saveThemeEvaluationFinal(Advice advice, Boolean isFinal) {
        Advice fetchedAdvice = (Advice)this.getOne(advice);
        if (fetchedAdvice == null || fetchedAdvice.getId() == null)
            fetchedAdvice = new Advice();
        fetchedAdvice.setConsiderations(advice.getConsiderations());
        fetchedAdvice.setStatus(advice.getStatus());
        fetchedAdvice.setIsFinal(isFinal);
        fetchedAdvice.setAppraiser(advice.getAppraiser());
        fetchedAdvice.setDocument(advice.getDocument());
       // fetchedAdvice = adviceRepository.save(fetchedAdvice);
        return getOne(adviceRepository.save(fetchedAdvice));
    }
}
