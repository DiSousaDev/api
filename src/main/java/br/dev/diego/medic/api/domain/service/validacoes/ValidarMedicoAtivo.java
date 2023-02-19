package br.dev.diego.medic.api.domain.service.validacoes;

import br.dev.diego.medic.api.domain.repositories.MedicoRepository;
import br.dev.diego.medic.api.domain.requests.AgendamentoRequestRecord;
import br.dev.diego.medic.api.domain.service.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidarMedicoAtivo implements ValidadorAgendamentoConsulta {

    private MedicoRepository repository;

    @Override
    public void execute(AgendamentoRequestRecord request) {

        if(request.idMedico() == null) {
            return;
        }

        Boolean medicoEstaAtivo = repository.findAtivoById(request.idMedico());

        if(medicoEstaAtivo == null) {
            return;
        }

        if(!medicoEstaAtivo) {
            throw new ValidationException("Médico não está ativo no momento.");
        }

    }

}
