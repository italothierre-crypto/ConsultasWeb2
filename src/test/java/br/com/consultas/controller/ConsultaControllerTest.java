package br.com.consultas.controller;

import br.com.consultas.model.Consulta;
import br.com.consultas.model.Medico;
import br.com.consultas.model.Paciente;
import br.com.consultas.service.ConsultaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ConsultaController.class)
@AutoConfigureMockMvc(addFilters = false)
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsultaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarConsultaComSucesso() throws Exception {

        // Criando paciente
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("João");

        // Criando médico
        Medico medico = new Medico();
        medico.setId(1L);
        medico.setNome("Dr. Carlos");

        // Criando consulta
        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setData(LocalDate.now());
        consulta.setHorario(LocalTime.now());
        consulta.setObservacao("Consulta teste");

        Mockito.when(service.salvar(Mockito.any(Consulta.class)))
                .thenReturn(consulta);

        mockMvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(consulta)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sucesso").value(true))
                .andExpect(jsonPath("$.mensagem").value("Consulta criada com sucesso"));
    }
}