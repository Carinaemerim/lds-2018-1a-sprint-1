package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;

@Data
public class TermPaperDTO {

    private String title;
    private String theme;

    private UserDTO author;

}
