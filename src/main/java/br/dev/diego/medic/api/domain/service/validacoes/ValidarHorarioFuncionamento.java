package br.dev.diego.medic.api.domain.service.validacoes;

import br.dev.diego.medic.api.domain.requests.AgendamentoRequestRecord;
import br.dev.diego.medic.api.domain.service.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidarHorarioFuncionamento implements ValidadorAgendamentoConsulta {

    private static final Integer INICIO_CONSULTAS = 7;
    private static final Integer FIM_CONSULTAS = 18;

    @Override
    public void execute(AgendamentoRequestRecord request) {
        LocalDateTime dataAgendamento = request.data();

        boolean isDomingo = dataAgendamento.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean isAntesDaAbertura = dataAgendamento.getHour() < INICIO_CONSULTAS;
        boolean isDepoisDaAbertura = dataAgendamento.getHour() > FIM_CONSULTAS;

        if(isDomingo || isAntesDaAbertura || isDepoisDaAbertura) {
            throw new ValidationException("Hoário indisponível para agendamento de consultas.");
        }

    }

}
