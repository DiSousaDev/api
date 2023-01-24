package br.dev.diego.medic.api.entities.records.responses;

import br.dev.diego.medic.api.entities.Medico;
import br.dev.diego.medic.api.entities.enums.Especialidade;

public record MedicoResponseRecord(String nome, String email, String crm, Especialidade especialidade) {

        public MedicoResponseRecord(Medico medico) {
                this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
        }
}
