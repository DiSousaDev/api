package br.dev.diego.medic.api.domain.requests;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestRecord(
        @NotBlank(message = "{login.obrigatorio}")
        String login,
        @NotBlank(message = "{senha.obrigatorio}")
        String senha) {
}
