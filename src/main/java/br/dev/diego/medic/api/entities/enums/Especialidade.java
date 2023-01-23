package br.dev.diego.medic.api.entities.enums;

public enum Especialidade {

    ORTOPEDIA("ortopedia"),
    CARDIOLODIA("cardiolodia"),
    GINECOLOGIA("ginecologia"),
    DERMATOLOGIA("dermatologia");

    private String descricao;

    Especialidade(String descricao) {
        this.descricao = descricao;
    }
}
