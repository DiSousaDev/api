package br.dev.diego.medic.api.entities.records.requests;

import br.dev.diego.medic.api.entities.records.EnderecoRecord;

public record MedicoUpdateRequestRecord(
        String nome,
        String telefone,
        EnderecoRecord endereco) {
}
