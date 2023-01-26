package br.dev.diego.medic.api.domain.medico;

import br.dev.diego.medic.api.domain.endereco.EnderecoRecord;

public record MedicoUpdateRequestRecord(
        String nome,
        String telefone,
        EnderecoRecord endereco) {
}
