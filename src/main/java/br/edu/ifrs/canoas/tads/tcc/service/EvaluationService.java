package br.edu.ifrs.canoas.tads.tcc.service;

import br.edu.ifrs.canoas.tads.tcc.domain.*;
import br.edu.ifrs.canoas.tads.tcc.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by cassiano on 3/21/18.
 */
@Service
@AllArgsConstructor
public class EvaluationService {

    DocumentRepository documentRepository;

    public String testMap() {

        // Testando o DTO
        List<Document> documents = documentRepository.findByIsFinalTrueAndDocumentType(DocumentType.THEME);
        if (documents != null) {

            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);


            Converter<TermPaper, String> conversor = new Converter<TermPaper, String>() {

                public String convert(MappingContext<TermPaper, String> context) {
                    final TermPaper tp = context.getSource();
                    return tp.toString();
                }
            };

            mapper.addConverter(conversor);
            for(Document doc: documents){
                DocumentDTO dtoDocument = mapper.map(doc, DocumentDTO.class);
                System.out.println(dtoDocument.toString());
            }


        } else {
            System.out.println("\nVazio");
        }

        return null;
    }

}
