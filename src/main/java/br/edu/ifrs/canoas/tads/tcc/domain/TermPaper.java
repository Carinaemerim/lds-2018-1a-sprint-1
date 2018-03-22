package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
public class TermPaper {
	@Id @GeneratedValue
    private Long id;
	@NotBlank
	private String title;
	@NotBlank
	private String theme;
	@NotBlank
	private String description;
	@ManyToOne
	private Student author;
    @ManyToOne
    private Professor advisor;

    /* Gerar a partir do abstract da monografia*/
    @Transient
    public String getAbstract(){
        return "Assim como o título, o resumo e o abstract do seu trabalho é a porta de entrada para o leitor, " +
                "além de dar uma visão geral do seu trabalho, deve despertar o interesse do mesmo. Como o resumo e " +
                "abstract possue uma quantidade de texto limitada, muitas pessoas tem dificuldade em elaborar um texto " +
                "conciso e interessante. Desta forma, vamos apresentar uma técnica para facilitar a elaboração " +
                "do resumo e o abstract que consiste em dividi-los em cinco partes: contexto, objetivo, método, " +
                "resultados e conclusão. Veja a seguir do que se trata cada uma dessas partes.";
    }

    @OneToMany(mappedBy="termPaper")
	private List<Document> documents;

}
