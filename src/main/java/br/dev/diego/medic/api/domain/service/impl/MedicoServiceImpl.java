package br.dev.diego.medic.api.domain.service.impl;

import br.dev.diego.medic.api.domain.entities.Medico;
import br.dev.diego.medic.api.domain.requests.MedicoRequestRecord;
import br.dev.diego.medic.api.domain.requests.MedicoUpdateRequestRecord;
import br.dev.diego.medic.api.domain.responses.MedicoFullResponseRecord;
import br.dev.diego.medic.api.domain.responses.MedicoResponseRecord;
import br.dev.diego.medic.api.domain.repositories.MedicoRepository;
import br.dev.diego.medic.api.domain.service.MedicoService;
import jakarta.persistence.EntityNotFoundException;
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
    @Transactional(readOnly = true)
    public Page<MedicoResponseRecord> buscarTodosAtivos(Pageable pageable) {
        return repository.findAllMedicoByAtivo(pageable, true).map(MedicoResponseRecord::new);
    }

    @Override
    @Transactional
    public MedicoFullResponseRecord cadastrar(MedicoRequestRecord request) {
        return new MedicoFullResponseRecord(repository.save(new Medico(request)));
    }

    @Override
    @Transactional(readOnly = true)
    public MedicoFullResponseRecord buscarPorId(Long id) {
        return new MedicoFullResponseRecord(repository.getReferenceById(id));
    }

    @Override
    @Transactional
    public MedicoFullResponseRecord atualizar(Long id, MedicoUpdateRequestRecord request) {
        Medico entity = repository.getReferenceById(id);
        entity.atualizar(request);
        return new MedicoFullResponseRecord(entity);
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void desativar(Long id) {
        Medico entity = getEntityById(id);
        entity.desativaMedico();
        repository.save(entity);
    }

    private Medico getEntityById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Medico não encontrado"));
    }

}
