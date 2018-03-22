package br.edu.ifrs.canoas.tads.tcc.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.tads.tcc.domain.Document;
import br.edu.ifrs.canoas.tads.tcc.domain.DocumentType;
import br.edu.ifrs.canoas.tads.tcc.repository.DocumentRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DocumentService {

	private final DocumentRepository documentRepository;

	public Iterable<Document> search(DocumentType documentType) {
        return documentType!=null?
                documentRepository.findByDocumentType(documentType):
                new ArrayList();
    }

	public Iterable<Document> findAll(){
		return documentRepository.findAll();
	}

	public void deleteOne(Long id){

		documentRepository.deleteById(id);
	}


}
