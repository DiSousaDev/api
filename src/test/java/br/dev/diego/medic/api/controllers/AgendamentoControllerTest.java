package br.dev.diego.medic.api.controllers;

import br.dev.diego.medic.api.domain.enums.Especialidade;
import br.dev.diego.medic.api.domain.requests.AgendamentoRequestRecord;
import br.dev.diego.medic.api.domain.responses.AgendamentoFullResponseRecord;
import br.dev.diego.medic.api.domain.responses.MedicoResponseRecord;
import br.dev.diego.medic.api.domain.responses.PacienteResponseRecord;
import br.dev.diego.medic.api.domain.service.AgendamentoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AgendamentoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AgendamentoRequestRecord> dadosAgendamentoConsultaJson;

    @Autowired
    private JacksonTester<AgendamentoFullResponseRecord> dadosDetalhamentoConsultaJson;

    @MockBean
    private AgendamentoService service;


    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void agendar_cenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void agendar_cenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        var dadosDetalhamento = new AgendamentoFullResponseRecord(null, getMedicoResponse(), getPacienteResponse(), data);
        when(service.agendarConsulta(any())).thenReturn(dadosDetalhamento);

        var response = mvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAgendamentoConsultaJson.write(
                                        new AgendamentoRequestRecord(2l, 5l, data, especialidade)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJson.write(
                dadosDetalhamento
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private MedicoResponseRecord getMedicoResponse() {
        return new MedicoResponseRecord(
                1l,
                "J??o Silva",
                "joao@medic.com",
                "123456789",
                Especialidade.GINECOLOGIA
        );
    }

    private PacienteResponseRecord getPacienteResponse() {
        return new PacienteResponseRecord(
                1l,
                "Carlos Alberto",
                "carlos@paciente.com",
                "12312312311"
        );
    }

}