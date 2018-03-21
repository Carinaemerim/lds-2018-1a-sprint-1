package br.edu.ifrs.canoas.tads.tcc.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.tads.tcc.domain.Document;
import br.edu.ifrs.canoas.tads.tcc.domain.DocumentType;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.repository.DocumentRepository;
import lombok.AllArgsConstructor;

/**
 * Created by nicolas on 3/20/18.
 */
@Service
@AllArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public Document getOne(Document document) {
    	if (document == null || document.getId() == null)
    		return null;
    	Optional<Document> optionalDocument = documentRepository.findById(document.getId());
    	return optionalDocument.isPresent() ? optionalDocument.get() : null;
    }

	public Document save(Document document) {
		Document fetchedDocument = this.getOne(document);
		if (fetchedDocument == null || fetchedDocument.getId() == null)
			fetchedDocument = new Document();
		fetchedDocument.setDocumentType(document.getDocumentType());
		fetchedDocument.setEvaluations(document.getEvaluations());
		fetchedDocument.setFile(document.getFile());
		fetchedDocument.setIsFinal(document.getIsFinal());
		fetchedDocument.setTermPaper(document.getTermPaper());
		return documentRepository.save(document);
	}

	public Document getFinalThemeDocumentByTermPaper(TermPaper termPaper) {
		if (termPaper == null)
			return null;
		return documentRepository.findLastByTermPaperIdAndIsFinalTrueAndDocumentType(termPaper.getId(), DocumentType.THEME);
	}

	public Iterable<Document> getIsFinalTrueAndDocumentType(DocumentType theme) {
		return theme!=null?
				documentRepository.findByIsFinalTrueAndDocumentType(theme):
				new ArrayList();
	}

}
