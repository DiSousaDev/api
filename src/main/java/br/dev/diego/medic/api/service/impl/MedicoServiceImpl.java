package br.dev.diego.medic.api.service.impl;

import br.dev.diego.medic.api.entities.Medico;
import br.dev.diego.medic.api.entities.records.requests.MedicoRequestRecord;
import br.dev.diego.medic.api.repositories.MedicoRepository;
import br.dev.diego.medic.api.service.MedicoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private MedicoRepository repository;

    @Override
    @Transactional
    public void cadastrar(MedicoRequestRecord request) {
        repository.save(new Medico(request));
    }
}
