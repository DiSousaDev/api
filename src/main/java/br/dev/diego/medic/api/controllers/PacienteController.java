package br.dev.diego.medic.api.controllers;

import br.dev.diego.medic.api.domain.entities.Paciente;
import br.dev.diego.medic.api.domain.repositories.PacienteRepository;
import br.dev.diego.medic.api.domain.requests.PacienteRequestRecord;
import br.dev.diego.medic.api.domain.requests.PacienteUpdateRequestRecord;
import br.dev.diego.medic.api.domain.responses.PacienteFullResponseRecord;
import br.dev.diego.medic.api.domain.responses.PacienteResponseRecord;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("pacientes")
public class PacienteController {

    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteFullResponseRecord> cadastrar(@RequestBody @Valid PacienteRequestRecord dados) {
        var paciente = new Paciente(dados);
        var response = new PacienteFullResponseRecord(repository.save(paciente));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.id()).toUri();
        log.info("Medico cadastrado com sucesso.");
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<PacienteResponseRecord>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(PacienteResponseRecord::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PacienteFullResponseRecord> atualizar(@RequestBody @Valid PacienteUpdateRequestRecord dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

        return ResponseEntity.ok(new PacienteFullResponseRecord(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteFullResponseRecord> detalhar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new PacienteFullResponseRecord(paciente));
    }


}
