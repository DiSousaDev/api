package br.dev.diego.medic.api.domain.service;

import br.dev.diego.medic.api.domain.requests.MedicoRequestRecord;
import br.dev.diego.medic.api.domain.requests.MedicoUpdateRequestRecord;
import br.dev.diego.medic.api.domain.responses.MedicoFullResponseRecord;
import br.dev.diego.medic.api.domain.responses.MedicoResponseRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MedicoService {

    Page<MedicoResponseRecord> buscarTodosAtivos(Pageable pageable);
    MedicoFullResponseRecord cadastrar(MedicoRequestRecord request);
    MedicoFullResponseRecord buscarPorId(Long id);
    MedicoFullResponseRecord atualizar(Long id, MedicoUpdateRequestRecord request);
    void excluir(Long id);
    void desativar(Long id);
}
