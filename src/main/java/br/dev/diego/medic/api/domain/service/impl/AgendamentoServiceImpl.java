package br.dev.diego.medic.api.domain.service.impl;

import br.dev.diego.medic.api.domain.entities.Agendamento;
import br.dev.diego.medic.api.domain.entities.Medico;
import br.dev.diego.medic.api.domain.entities.Paciente;
import br.dev.diego.medic.api.domain.repositories.AgendamentoRepository;
import br.dev.diego.medic.api.domain.repositories.MedicoRepository;
import br.dev.diego.medic.api.domain.repositories.PacienteRepository;
import br.dev.diego.medic.api.domain.requests.AgendamentoRequestRecord;
import br.dev.diego.medic.api.domain.responses.AgendamentoFullResponseRecord;
import br.dev.diego.medic.api.domain.service.AgendamentoService;
import br.dev.diego.medic.api.domain.service.exceptions.ValidationException;
import br.dev.diego.medic.api.domain.service.validacoes.ValidadorAgendamentoConsulta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AgendamentoServiceImpl implements AgendamentoService {

    private AgendamentoRepository repository;
    private MedicoRepository medicoRepository;
    private PacienteRepository pacienteRepository;
    private List<ValidadorAgendamentoConsulta> validadores;

    @Override
    public AgendamentoFullResponseRecord agendarConsulta(AgendamentoRequestRecord request) {
        Medico medico = selecionarMedico(request);
        Paciente paciente = getPacienteById(request.idPaciente());
        Agendamento save = repository.save(new Agendamento(null, medico, paciente, request.data()));
        return new AgendamentoFullResponseRecord(save);
    }

    private Medico selecionarMedico(AgendamentoRequestRecord request) {

        validadores.forEach(validador -> validador.execute(request));

        if(request.idMedico() != null) {
            return getMedicoById(request.idMedico());
        }

        if(request.especialidade() == null) {
            throw new ValidationException("Informe a especialidade do médico desejado");
        }

        Medico medico = medicoRepository.selecionarMedicoAleatorioLivreNaData(request.especialidade(), request.data());

        if(medico == null) {
            throw new ValidationException("Nenhum médico disponível para agendamento.");
        }

        return  medico;
    }

    private Medico getMedicoById(Long id) {
        return medicoRepository.findById(id).orElseThrow(() -> new ValidationException("Medico não encontrado"));
    }

    private Paciente getPacienteById(Long id) {
        return pacienteRepository.findById(id).orElseThrow(() -> new ValidationException("Paciente não encontrado"));
    }

}
