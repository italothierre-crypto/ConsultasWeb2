package br.com.consultas.controller;

import br.com.consultas.model.Consulta;
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
@AutoConfigureMockMvc(addFilters = false) // desativa segurança para teste
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsultaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarConsultaComSucesso() throws Exception {

        Consulta consulta = new Consulta();
        consulta.setData(LocalDate.now());
        consulta.setHorario(LocalTime.now());
        consulta.setObservacao("Consulta teste");

        Mockito.when(service.salvar(Mockito.any(Consulta.class)))
                .thenReturn(consulta);

        mockMvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(consulta)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Consulta criada com sucesso"));
    }
}