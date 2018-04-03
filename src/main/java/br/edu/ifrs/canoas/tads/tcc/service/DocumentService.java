package br.edu.ifrs.canoas.tads.tcc.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.tads.tcc.config.Messages;
import br.edu.ifrs.canoas.tads.tcc.domain.Document;
import br.edu.ifrs.canoas.tads.tcc.domain.DocumentType;
import br.edu.ifrs.canoas.tads.tcc.domain.EvaluationBoard;
import br.edu.ifrs.canoas.tads.tcc.domain.EvaluationStatus;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.repository.DocumentRepository;
import br.edu.ifrs.canoas.tads.tcc.repository.EvaluationBoardRepository;
import lombok.AllArgsConstructor;

/**
 * Created by nicolas on 3/20/18.
 */
@Service
@AllArgsConstructor
public class DocumentService {

	private final Messages messages;
	private final DocumentRepository documentRepository;
	private final EvaluationBoardRepository evaluationBoardRepository;

	public Document getOneById(Long id) {
		return documentRepository.getOne(id);
	}

	public Document getOne(Document document) {
		if (document == null || document.getId() == null)
			return null;
		Optional<Document> optionalDocument = documentRepository.findById(document.getId());
		return optionalDocument.isPresent() ? optionalDocument.get() : null;
	}

	public Document createOrUpdateThemeDocument(TermPaper termPaper) {
		if (termPaper == null) {
			throw new RuntimeException(messages.get("theme.termPaperNotDefined"));
		}
		if (termPaper.getAdvisor() == null) {
			throw new RuntimeException(messages.get("theme.advisorNotDefined"));
		}
		Document fetchedDocument = this.getFinalThemeDocumentByTermPaper(termPaper);
		if (fetchedDocument == null) {
			fetchedDocument = new Document();
			fetchedDocument.setCreatedOn(Calendar.getInstance().getTime());
		} else if (fetchedDocument.getStatus().equals(EvaluationStatus.REDO) || fetchedDocument.getStatus().equals(EvaluationStatus.DISAPPROVED)) {
			fetchedDocument.setIsFinal(false);
			documentRepository.save(fetchedDocument);
			fetchedDocument = new Document();
			fetchedDocument.setCreatedOn(Calendar.getInstance().getTime());
		}
		fetchedDocument.setTitle(termPaper.getTitle());
		fetchedDocument.setDocumentType(DocumentType.THEME);
		fetchedDocument.setIsFinal(true);
		fetchedDocument.setTermPaper(termPaper);
		fetchedDocument = documentRepository.save(fetchedDocument);
		EvaluationBoard evaluationBoard = evaluationBoardRepository.findFirstByDocumentId(fetchedDocument.getId());
		if (termPaper.getAdvisor() != null) {
			if (evaluationBoard == null) {
				evaluationBoard = new EvaluationBoard();
				evaluationBoard.setDocument(fetchedDocument);
			}
			evaluationBoard.setProfessors(new ArrayList<>());
			evaluationBoard.getProfessors().add(fetchedDocument.getTermPaper().getAdvisor());
			evaluationBoard = evaluationBoardRepository.save(evaluationBoard);
			fetchedDocument.setEvaluationBoard(evaluationBoard);
			fetchedDocument = documentRepository.save(fetchedDocument);
		}
		return fetchedDocument;
	}

	public Document getFinalThemeDocumentByTermPaper(TermPaper termPaper) {
		if (termPaper == null)
			return null;
		return documentRepository.findFirstByTermPaperIdAndIsFinalTrueAndDocumentTypeOrderByCreatedOnDesc(termPaper.getId(),
				DocumentType.THEME);
	}

	public Iterable<Document> getIsFinalTrueAndDocumentType(DocumentType theme) {
		return theme != null ? documentRepository.findByIsFinalTrueAndDocumentType(theme) : new ArrayList();
	}

	public Iterable<Document> search(DocumentType documentType) {
		return documentType != null ? documentRepository.findByDocumentType(documentType) : new ArrayList();
	}

	public Iterable<Document> findAll() {
		return documentRepository.findAll();
	}

	public void deleteOne(Long id) {

		documentRepository.deleteById(id);
	}
}
