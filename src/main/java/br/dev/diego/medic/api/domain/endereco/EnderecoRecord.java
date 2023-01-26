package br.dev.diego.medic.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoRecord(
        @NotBlank(message = "O campo logradouro deve ser preenchido.")
        String logradouro,
        @NotBlank(message = "O campo bairro deve ser preenchido.")
        String bairro,
        @NotBlank(message = "O campo cep deve ser preenchido.")
                @Pattern(regexp = "\\d{8}", message = "Insira um cep válido.")
        String cep,
        @NotBlank(message = "O campo cidade deve ser preenchido.")
        String cidade,
        @NotBlank(message = "O campo uf deve ser preenchido.")
        String uf,
        String complemento,
        String numero) {

        public EnderecoRecord(Endereco entity) {
                this(entity.getLogradouro(), entity.getBairro(), entity.getCep(), entity.getCidade(), entity.getUf(), entity.getComplemento(), entity.getNumero());
        }

}
