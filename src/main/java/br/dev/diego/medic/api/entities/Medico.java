package br.dev.diego.medic.api.entities;

import br.dev.diego.medic.api.entities.enums.Especialidade;
import br.dev.diego.medic.api.entities.records.requests.MedicoRequestRecord;
import br.dev.diego.medic.api.entities.records.requests.MedicoUpdateRequestRecord;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
    private Boolean ativo;

    public Medico(MedicoRequestRecord request) {
        this.nome = request.nome();
        this.email = request.email();
        this.crm = request.crm();
        this.telefone = request.telefone();
        this.especialidade = request.especialidade();
        this.endereco = new Endereco(request.endereco());
        this.ativo = true;
    }

    public void atualizar(MedicoUpdateRequestRecord request) {
        this.nome = request.nome() != null ?  request.nome() : this.nome;
        this.telefone = request.telefone() != null ? request.telefone() : this.telefone;

        if(request.endereco() != null) {
            this.endereco.atualizar(request.endereco());
        }

    }

    public void desativaMedico() {
        this.ativo = false;
    }

}
