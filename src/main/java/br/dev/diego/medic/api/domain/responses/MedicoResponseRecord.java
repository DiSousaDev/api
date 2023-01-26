package br.dev.diego.medic.api.domain.responses;

import br.dev.diego.medic.api.domain.entities.Medico;
import br.dev.diego.medic.api.domain.enums.Especialidade;

public record MedicoResponseRecord(String nome, String email, String crm, Especialidade especialidade) {

        public MedicoResponseRecord(Medico medico) {
                this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
        }
}
