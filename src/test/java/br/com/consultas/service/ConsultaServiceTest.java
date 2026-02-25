package br.com.consultas.service;

import br.com.consultas.model.Consulta;
import br.com.consultas.repository.ConsultaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {

    @Mock
    private ConsultaRepository repository;

    @InjectMocks
    private ConsultaService service;

    @Test
    void deveSalvarConsulta() {

        Consulta consulta = new Consulta();
        consulta.setObservacao("Consulta teste");

        when(repository.save(any())).thenReturn(consulta);

        Consulta salva = service.salvar(consulta);

        assertNotNull(salva);
        assertEquals("Consulta teste", salva.getObservacao());

        verify(repository, times(1)).save(consulta);
    }
}