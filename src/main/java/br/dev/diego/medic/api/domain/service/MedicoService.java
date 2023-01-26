package br.dev.diego.medic.api.domain.service;

import br.dev.diego.medic.api.domain.medico.MedicoRequestRecord;
import br.dev.diego.medic.api.domain.medico.MedicoUpdateRequestRecord;
import br.dev.diego.medic.api.domain.medico.MedicoFullResponseRecord;
import br.dev.diego.medic.api.domain.medico.MedicoResponseRecord;
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
