package br.dev.diego.medic.api.domain.entities;

import br.dev.diego.medic.api.domain.requests.EnderecoRecord;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tb_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;
    @OneToOne(mappedBy = "endereco")
    private Medico medico;
    @OneToOne(mappedBy = "endereco")
    private Paciente paciente;

    public Endereco(EnderecoRecord endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
        this.complemento = endereco.complemento();
        this.numero = endereco.numero();
    }

    public void atualizar(EnderecoRecord endereco) {
        this.logradouro = endereco.logradouro() != null ? endereco.logradouro() : this.logradouro;
        this.bairro = endereco.bairro() != null ? endereco.bairro() : this.bairro;
        this.cep = endereco.cep() != null ? endereco.cep() : this.cep;
        this.cidade = endereco.cidade() != null ? endereco.cidade() : this.cidade;
        this.uf = endereco.uf() != null ? endereco.uf() : this.uf;
        this.complemento = endereco.complemento() != null ? endereco.complemento() : this.complemento;
        this.numero = endereco.numero() != null ? endereco.numero() : this.numero;
    }
}
