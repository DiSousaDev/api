package br.dev.diego.medic.api.domain.responses;

import br.dev.diego.medic.api.domain.entities.Paciente;

public record PacienteResponseRecord(Long id, String nome, String email, String cpf) {

    public PacienteResponseRecord(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }

}
