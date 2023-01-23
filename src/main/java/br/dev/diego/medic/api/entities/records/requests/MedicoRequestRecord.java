package br.dev.diego.medic.api.entities.records.requests;

import br.dev.diego.medic.api.entities.enums.Especialidade;

public record MedicoRequestRecord(String nome, String email, String crm, Especialidade especialidade, EnderecoRecord endereco) {
}
