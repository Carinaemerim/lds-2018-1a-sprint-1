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
            for(Document doc: documents){
                DocumentDTO dtoDocument = mapper.map(doc, DocumentDTO.class);
                System.out.println(dtoDocument.toString());
            }


        } else {
            System.out.println("\nVazio");
        }

        return null;
    }
    
    public Iterable<TermPaper> listTermPaperEvaluation(User user){
    	//return termPaperRepository.findByAllTermPaperForEvaluation();
    		return null;
//            return user!=null?
//                    termPaperRepository.findByAllTermPaperForEvaluation():
//                    new ArrayList();   	
    }

}
