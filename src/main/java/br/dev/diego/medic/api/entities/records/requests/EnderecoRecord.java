package br.dev.diego.medic.api.entities.records.requests;

public record EnderecoRecord(String logradouro, String bairro, String cep, String cidade, String uf, String complemento, String numero) {
}
