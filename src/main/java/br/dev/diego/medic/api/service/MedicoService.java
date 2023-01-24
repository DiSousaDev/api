package br.dev.diego.medic.api.service;

import br.dev.diego.medic.api.entities.records.requests.MedicoRequestRecord;

public interface MedicoService {

    void cadastrar(MedicoRequestRecord request);

}
