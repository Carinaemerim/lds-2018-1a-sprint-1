package br.edu.ifrs.canoas.tads.tcc.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(of = { "id", "title", "theme" })
@ToString(of = { "id", "title", "theme" })
public class TermPaper {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	@Size(max=250)
	private String title;

	@NotBlank
	@Size(max=50)
	private String theme;

	@NotBlank
	@Size(max=500)
	private String description;

	@ManyToOne
	private Student author;

	@ManyToOne
	private Professor advisor;

	@ManyToOne
	private AcademicYear academicYear;

	/* Gerar a partir do abstract da monografia */
	@Transient
	public String getAbstract() {
		return "Assim como o título, o resumo e o abstract do seu trabalho é a porta de entrada para o leitor, "
				+ "além de dar uma visão geral do seu trabalho, deve despertar o interesse do mesmo. Como o resumo e "
				+ "abstract possue uma quantidade de texto limitada, muitas pessoas tem dificuldade em elaborar um texto "
				+ "conciso e interessante. Desta forma, vamos apresentar uma técnica para facilitar a elaboração "
				+ "do resumo e o abstract que consiste em dividi-los em cinco partes: contexto, objetivo, método, "
				+ "resultados e conclusão. Veja a seguir do que se trata cada uma dessas partes.";
	}

	@OneToMany(mappedBy = "termPaper", fetch = FetchType.EAGER)
	private List<Document> documents;

	@Transient
	public Boolean getThemeSubmitted() {
		return this.getThemeDocument() != null && this.getThemeDocument().getStatus() != EvaluationStatus.DISAPPROVED
				&& this.getThemeDocument().getStatus() != EvaluationStatus.REDO;
	}

	@Transient
	public Document getThemeDocument() {
		if (documents != null)
			for (Document doc : this.documents) {
				if (doc.getDocumentType().equals(DocumentType.THEME) && doc.getIsFinal()) {
					return doc;
				}
			}
		return null;
	}

	public Document getProposalDocument() {
		if (documents != null)
			for (Document doc : documents) {
				if (doc.getDocumentType().equals(DocumentType.PROPOSAL) && doc.getIsFinal()) {
					return doc;
				}
			}
		return null;
	}

	public Document getTermPaperDocument() {
		if (documents != null)
			for (Document doc : documents) {
				if (doc.getDocumentType().equals(DocumentType.TERMPAPER) && doc.getIsFinal()) {
					return doc;
				}
			}
		return null;
	}
}