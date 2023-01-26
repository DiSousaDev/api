package br.dev.diego.medic.api.domain.responses;

import br.dev.diego.medic.api.domain.entities.Medico;
import br.dev.diego.medic.api.domain.enums.Especialidade;
import br.dev.diego.medic.api.domain.requests.EnderecoRecord;

public record MedicoFullResponseRecord(Long id, String nome, String email, String crm, String telefone,
                                       Especialidade especialidade, Boolean ativo, EnderecoRecord endereco) {

    public MedicoFullResponseRecord(Medico entity) {
        this(entity.getId(), entity.getNome(), entity.getEmail(), entity.getCrm(), entity.getTelefone(), entity.getEspecialidade(), entity.getAtivo(), new EnderecoRecord(entity.getEndereco()));
    }

}
