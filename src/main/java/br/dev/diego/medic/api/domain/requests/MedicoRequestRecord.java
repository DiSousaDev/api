package br.dev.diego.medic.api.domain.requests;

import br.dev.diego.medic.api.domain.enums.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoRequestRecord(
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,
        @NotBlank(message = "{email.obrigatorio}")
        @Email(message = "{email.invalido}")
        String email,
        @NotBlank(message = "{crm.obrigatorio}")
        @Pattern(regexp = "\\d{4,6}", message = "{crm.invalido}")
        String crm,
        @NotBlank(message = "{telefone.obrigatorio}")
        String telefone,
        @NotNull(message = "{especialidade.obrigatorio}")
        Especialidade especialidade,
        @Valid
        @NotNull(message = "{endereco.obrigatorio}")
        EnderecoRecord endereco) {
}
