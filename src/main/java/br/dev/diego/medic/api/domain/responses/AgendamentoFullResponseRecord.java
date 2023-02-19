package br.dev.diego.medic.api.domain.responses;

import br.dev.diego.medic.api.domain.entities.Agendamento;

import java.time.LocalDateTime;

public record AgendamentoFullResponseRecord(Long id, MedicoResponseRecord medico, PacienteResponseRecord paciente,
                                            LocalDateTime data) {
    public AgendamentoFullResponseRecord(Agendamento entity) {
        this(
                entity.getId(),
                new MedicoResponseRecord(entity.getMedico()),
                new PacienteResponseRecord(entity.getPaciente()),
                entity.getData()
        );
    }
}
