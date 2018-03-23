package br.edu.ifrs.canoas.tads.tcc.service;

import br.edu.ifrs.canoas.tads.tcc.domain.*;
import br.edu.ifrs.canoas.tads.tcc.repository.DocumentRepository;
import br.edu.ifrs.canoas.tads.tcc.repository.EvaluationRepository;
import br.edu.ifrs.canoas.tads.tcc.repository.GradeRepository;
import br.edu.ifrs.canoas.tads.tcc.repository.TermPaperRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

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
    public Evaluation getOneEvaluationById(Long id) {
        return evaluationRepository.getOne(id);
    }
    public Grade getOneGradeById(Long id) {
        return gradeRepository.getOne(id);
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

    public Document getOne(Document document) {
        if (document == null || document.getId() == null)
            return null;
        Optional<Document> optionalDocument = documentRepository.findById(document.getId());
        return optionalDocument.isPresent() ? optionalDocument.get() : null;
    }
    public String testMap() {

        // Testando o DTO
        List<Document> documents = documentRepository.findByIsFinalTrueAndDocumentType(DocumentType.THEME);
        if (documents != null) {

            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);


            Converter<TermPaper, String> conversor = context -> {
                final TermPaper tp = context.getSource();
                return tp.toString();
            };

            mapper.addConverter(conversor);
            for (Document doc : documents) {
                DocumentDTO dtoDocument = mapper.map(doc, DocumentDTO.class);
                System.out.println(dtoDocument.toString());
            }


        } else {
            System.out.println("\nVazio");
        }

        return null;
    }

    public Iterable<TermPaper> getTermPaperEvaluation(User user) {

       /* return user != null ?
                termPaperRepository.getTermPaperForEvaluation() :
                new ArrayList();*/
        final List<TermPaper> listWithDuplicates =  user != null ?
                termPaperRepository.getTermPaperForEvaluation() : new ArrayList();


        if(listWithDuplicates != null) {
            List<TermPaper> listWithoutDuplicates =  listWithDuplicates.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(TermPaper::getId))),
                    ArrayList::new));
            return  listWithoutDuplicates;
        }
        return listWithDuplicates;
    }

}
