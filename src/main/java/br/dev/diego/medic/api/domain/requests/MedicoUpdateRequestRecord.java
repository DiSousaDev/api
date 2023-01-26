package br.dev.diego.medic.api.domain.requests;

public record MedicoUpdateRequestRecord(
        String nome,
        String telefone,
        EnderecoRecord endereco) {
}
