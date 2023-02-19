package br.dev.diego.medic.api.domain.repositories;

import br.dev.diego.medic.api.domain.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    Boolean existsByMedicoIdAndData(Long id, LocalDateTime data);
    Boolean existsByPacienteIdAndDataBetween(Long id, LocalDateTime horaInicial, LocalDateTime horaFinal);

}
