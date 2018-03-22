package br.edu.ifrs.canoas.tads.tcc.service;

import br.edu.ifrs.canoas.tads.tcc.domain.Document;
import br.edu.ifrs.canoas.tads.tcc.domain.DocumentDTO;
import br.edu.ifrs.canoas.tads.tcc.domain.DocumentType;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.domain.User;
import br.edu.ifrs.canoas.tads.tcc.repository.DocumentRepository;
import br.edu.ifrs.canoas.tads.tcc.repository.TermPaperRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
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
