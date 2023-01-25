package br.dev.diego.medic.api.controllers;

import br.dev.diego.medic.api.entities.records.requests.MedicoRequestRecord;
import br.dev.diego.medic.api.entities.records.requests.MedicoUpdateRequestRecord;
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
@AllArgsConstructor
@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<Page<MedicoResponseRecord>> buscarMedicosAtivos(@PageableDefault(
            size = 6,
            sort = "nome",
            direction = Sort.Direction.DESC
    ) Pageable pageable) {
        return ResponseEntity.ok(medicoService.buscarTodosAtivos(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicoFullResponseRecord> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<MedicoFullResponseRecord> cadastrar(@RequestBody @Valid MedicoRequestRecord request) {
        MedicoFullResponseRecord obj = medicoService.cadastrar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.id()).toUri();
        log.info("Medico cadastrado com sucesso.");
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MedicoFullResponseRecord> atualizarMedico(@PathVariable Long id, @RequestBody @Valid MedicoUpdateRequestRecord request) {
        return ResponseEntity.ok(medicoService.atualizar(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirMedico(@PathVariable Long id) {
        medicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/desativar/{id}")
    public ResponseEntity<Void> desativarMedico(@PathVariable Long id) {
        medicoService.desativar(id);
        return ResponseEntity.noContent().build();
    }

}
