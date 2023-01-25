package br.dev.diego.medic.api.entities.records.responses;

import br.dev.diego.medic.api.entities.Medico;
import br.dev.diego.medic.api.entities.enums.Especialidade;
import br.dev.diego.medic.api.entities.records.EnderecoRecord;

public record MedicoFullResponseRecord(Long id, String nome, String email, String crm, String telefone,
                                       Especialidade especialidade, Boolean ativo, EnderecoRecord endereco) {

    public MedicoFullResponseRecord(Medico entity) {
        this(entity.getId(), entity.getNome(), entity.getEmail(), entity.getCrm(), entity.getTelefone(), entity.getEspecialidade(), entity.getAtivo(), new EnderecoRecord(entity.getEndereco()));
    }

}
