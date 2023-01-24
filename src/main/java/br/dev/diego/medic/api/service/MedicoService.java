package br.dev.diego.medic.api.service;

import br.dev.diego.medic.api.entities.records.requests.MedicoRequestRecord;
import br.dev.diego.medic.api.entities.records.requests.MedicoUpdateRequestRecord;
import br.dev.diego.medic.api.entities.records.responses.MedicoFullResponseRecord;
import br.dev.diego.medic.api.entities.records.responses.MedicoResponseRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MedicoService {

    Page<MedicoResponseRecord> buscarTodos(Pageable pageable);
    MedicoFullResponseRecord cadastrar(MedicoRequestRecord request);
    MedicoFullResponseRecord buscarPorId(Long id);
    MedicoFullResponseRecord atualizar(Long id, MedicoUpdateRequestRecord request);
}
