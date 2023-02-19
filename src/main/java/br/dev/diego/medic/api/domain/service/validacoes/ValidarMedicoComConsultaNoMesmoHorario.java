package br.dev.diego.medic.api.domain.service.validacoes;

import br.dev.diego.medic.api.domain.repositories.AgendamentoRepository;
import br.dev.diego.medic.api.domain.requests.AgendamentoRequestRecord;
import br.dev.diego.medic.api.domain.service.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidarMedicoComConsultaNoMesmoHorario implements ValidadorAgendamentoConsulta {

    private AgendamentoRepository repository;

    @Override
    public void execute(AgendamentoRequestRecord request) {

        boolean medicoPossuiConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(request.idMedico(), request.data());
        if(medicoPossuiConsultaNoMesmoHorario) {
            throw new ValidationException("Médico já possui consulta agendada neste horário");
        }

    }

}
