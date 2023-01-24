package br.dev.diego.medic.api.controllers;

import br.dev.diego.medic.api.entities.records.requests.MedicoRequestRecord;
import br.dev.diego.medic.api.entities.records.responses.MedicoFullResponseRecord;
import br.dev.diego.medic.api.entities.records.responses.MedicoResponseRecord;
import br.dev.diego.medic.api.service.MedicoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<Page<MedicoResponseRecord>> buscarTodos(@PageableDefault(
            size = 6,
            sort = "nome",
            direction = Sort.Direction.DESC
    ) Pageable pageable) {
        return ResponseEntity.ok(medicoService.buscarTodos(pageable));
    }

    @PostMapping
    public ResponseEntity<MedicoFullResponseRecord> cadastrar(@RequestBody @Valid MedicoRequestRecord request) {
        MedicoFullResponseRecord obj = medicoService.cadastrar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.id()).toUri();
        log.info("Medico cadastrado com sucesso.");
        return ResponseEntity.created(uri).body(obj);
    }

}
