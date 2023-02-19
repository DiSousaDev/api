package br.dev.diego.medic.api.domain.service.validacoes;

import br.dev.diego.medic.api.domain.repositories.PacienteRepository;
import br.dev.diego.medic.api.domain.requests.AgendamentoRequestRecord;
import br.dev.diego.medic.api.domain.service.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidarPacienteAtivo implements ValidadorAgendamentoConsulta {

    private PacienteRepository repository;

    @Override
    public void execute(AgendamentoRequestRecord request) {

        Boolean pacienteEstaAtivo = repository.findAtivoById(request.idPaciente());

        if(pacienteEstaAtivo == null) {
            return;
        }

        if(!pacienteEstaAtivo) {
            throw new ValidationException("Paciente não está ativo no momento.");
        }

    }

}
