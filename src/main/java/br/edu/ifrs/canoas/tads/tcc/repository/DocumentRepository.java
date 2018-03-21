package br.edu.ifrs.canoas.tads.tcc.repository;

import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.canoas.tads.tcc.domain.Document;
import br.edu.ifrs.canoas.tads.tcc.domain.DocumentType;

import java.util.List;


/**
 * Created by nicolas on 3/20/18.
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

	Document findLastByTermPaperIdAndIsFinalTrueAndDocumentType(Long id, DocumentType theme);


}