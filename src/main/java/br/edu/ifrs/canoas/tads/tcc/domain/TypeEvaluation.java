package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeEvaluation {
    NOTAPPLICABLE(0, "Não avaliativo"),
    EVALUATION(1,"Nota"),
    REPORT(2,"Parecer");

    private int id;
    private String description;

    public static TypeEvaluation parse(int id) {
        TypeEvaluation typeEvaluation = null;
        for (TypeEvaluation item : TypeEvaluation.values()) {
            if (item.getId() == id) {
                typeEvaluation = item;
                break;
            }
        }
        return typeEvaluation;
    }
    @Override
    public String toString() {
        return this.description;
    }
}
