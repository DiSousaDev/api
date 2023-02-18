package br.dev.diego.medic.api.domain.responses;

import br.dev.diego.medic.api.domain.entities.Endereco;
import br.dev.diego.medic.api.domain.entities.Paciente;

public record PacienteFullResponseRecord(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {

    public PacienteFullResponseRecord(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }
}
