package br.dev.diego.medic.api.domain.service.validacoes;

import br.dev.diego.medic.api.domain.requests.AgendamentoRequestRecord;

public interface ValidadorAgendamentoConsulta {

    void execute(AgendamentoRequestRecord request);

}
