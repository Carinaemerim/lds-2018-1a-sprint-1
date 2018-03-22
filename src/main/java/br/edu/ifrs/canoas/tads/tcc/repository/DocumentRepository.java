package br.edu.ifrs.canoas.tads.tcc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrs.canoas.tads.tcc.domain.Document;
import br.edu.ifrs.canoas.tads.tcc.domain.DocumentType;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;

import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.canoas.tads.tcc.domain.Document;
import br.edu.ifrs.canoas.tads.tcc.domain.DocumentType;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	List<Document> findByDocumentType(DocumentType documentType);

	Document findByFileCreatedOn(Date date);

	Document findLastByTermPaperIdAndIsFinalTrueAndDocumentType(Long id, DocumentType theme);
	List<Document> findByIsFinalTrueAndDocumentType(DocumentType theme);


}

