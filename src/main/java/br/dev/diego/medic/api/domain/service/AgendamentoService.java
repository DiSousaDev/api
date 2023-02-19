package br.dev.diego.medic.api.domain.service;

import br.dev.diego.medic.api.domain.requests.AgendamentoRequestRecord;
import br.dev.diego.medic.api.domain.responses.AgendamentoFullResponseRecord;

public interface AgendamentoService {

    AgendamentoFullResponseRecord agendarConsulta(AgendamentoRequestRecord request);

}
