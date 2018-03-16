package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TermPaper {
	@Id @GeneratedValue
    private Long id;
	private String title;
	private String theme;
	@ManyToOne
	private User author;
    @ManyToOne
    private User advisor;

}
