package br.dev.diego.medic.api.domain.service.validacoes;

import br.dev.diego.medic.api.domain.repositories.AgendamentoRepository;
import br.dev.diego.medic.api.domain.requests.AgendamentoRequestRecord;
import br.dev.diego.medic.api.domain.service.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class ValidarPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoConsulta {

    private AgendamentoRepository repository;

    @Override
    public void execute(AgendamentoRequestRecord request) {
        LocalDateTime primeiroHorario = request.data().withHour(7);
        LocalDateTime ultimoHorario = request.data().withHour(18);
        boolean pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(
                request.idPaciente(), primeiroHorario, ultimoHorario);

        if(pacientePossuiOutraConsultaNoDia) {
            throw new ValidationException("Paciente já possui consulta agendada neste dia");
        }

    }

}
