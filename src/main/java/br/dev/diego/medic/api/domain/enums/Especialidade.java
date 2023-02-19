package br.dev.diego.medic.api.domain.enums;

public enum Especialidade {

    ORTOPEDIA("ortopedia"),
    CARDIOLOGIA("cardiologia"),
    GINECOLOGIA("ginecologia"),
    DERMATOLOGIA("dermatologia");

    private String descricao;

    Especialidade(String descricao) {
        this.descricao = descricao;
    }
}
