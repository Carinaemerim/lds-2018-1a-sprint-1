package br.edu.ifrs.canoas.tads.tcc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrs.canoas.tads.tcc.domain.Document;
import br.edu.ifrs.canoas.tads.tcc.domain.DocumentType;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	List<Document> findByDocumentType(DocumentType documentType);

	Document findByFileCreatedOn(Date date);
}
