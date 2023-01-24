package br.dev.diego.medic.api.service.impl;

import br.dev.diego.medic.api.entities.Medico;
import br.dev.diego.medic.api.entities.records.requests.MedicoRequestRecord;
import br.dev.diego.medic.api.entities.records.responses.MedicoFullResponseRecord;
import br.dev.diego.medic.api.entities.records.responses.MedicoResponseRecord;
import br.dev.diego.medic.api.repositories.MedicoRepository;
import br.dev.diego.medic.api.service.MedicoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private MedicoRepository repository;

    @Override
    public Page<MedicoResponseRecord> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable).map(MedicoResponseRecord::new);
    }

    @Override
    @Transactional
    public MedicoFullResponseRecord cadastrar(MedicoRequestRecord request) {
        return new MedicoFullResponseRecord(repository.save(new Medico(request)));
    }

}
