package br.dev.diego.medic.api.controllers;

import br.dev.diego.medic.api.domain.requests.AgendamentoRequestRecord;
import br.dev.diego.medic.api.domain.responses.AgendamentoFullResponseRecord;
import br.dev.diego.medic.api.domain.service.AgendamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("consultas")
public class AgendamentoController {

    private AgendamentoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<AgendamentoFullResponseRecord> agendar(@RequestBody @Valid AgendamentoRequestRecord dados) {
        AgendamentoFullResponseRecord response = service.agendarConsulta(dados);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.id()).toUri();
        log.info("Consulta inserida com sucesso.");
        return ResponseEntity.created(uri).body(response);
    }

}
