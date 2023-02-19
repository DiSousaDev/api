package br.dev.diego.medic.api.domain.service.validacoes;

import br.dev.diego.medic.api.domain.requests.AgendamentoRequestRecord;
import br.dev.diego.medic.api.domain.service.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarHorarioAntecedencia implements ValidadorAgendamentoConsulta {

    private static final Integer ANTECEDENCIA_MINIMA = 30;

    @Override
    public void execute(AgendamentoRequestRecord request) {
        LocalDateTime dataAgendamento = request.data();
        LocalDateTime agora = LocalDateTime.now();
        long minutes = Duration.between(agora, dataAgendamento).toMinutes();

        if(minutes < ANTECEDENCIA_MINIMA) {
            throw new ValidationException("O Agendamento deve respeitar a antecedência minima de "
                    + ANTECEDENCIA_MINIMA + " minutos.");
        }
    }
}
