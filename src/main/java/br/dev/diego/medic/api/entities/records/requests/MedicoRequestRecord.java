package br.dev.diego.medic.api.entities.records.requests;

import br.dev.diego.medic.api.entities.enums.Especialidade;
import br.dev.diego.medic.api.entities.records.EnderecoRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoRequestRecord(
        @NotBlank(message = "O campo nome deve ser preenchido.")
        String nome,
        @Email(message = "O campo e-mail deve ser preenchido.")
        String email,
        @NotBlank(message = "O campo crm deve ser preenchido.")
        @Pattern(regexp = "\\d{4,6}", message = "Insira um crm válido.")
        String crm,
        @NotBlank(message = "O campo telefone deve ser preenchido.")
        String telefone,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        EnderecoRecord endereco) {
}
