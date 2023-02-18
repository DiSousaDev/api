package br.dev.diego.medic.api.domain.requests;

import jakarta.validation.constraints.NotNull;

public record PacienteUpdateRequestRecord(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoRecord endereco) {
}
