package br.dev.diego.medic.api.controllers;

import br.dev.diego.medic.api.entities.records.requests.MedicoRequestRecord;
import br.dev.diego.medic.api.service.MedicoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private MedicoService medicoService;

    @PostMapping
    public void cadastrar(@RequestBody @Valid MedicoRequestRecord request) {
        medicoService.cadastrar(request);
        log.info("Medico cadastrado com sucesso.");
    }

}
