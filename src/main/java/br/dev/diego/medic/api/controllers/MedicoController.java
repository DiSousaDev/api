package br.dev.diego.medic.api.controllers;

import br.dev.diego.medic.api.entities.records.requests.MedicoRequestRecord;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping
    public void cadastrar(@RequestBody MedicoRequestRecord request) {
        System.out.println(request);
    }

}
