package br.com.consultas.service;

import br.com.consultas.model.Consulta;
import br.com.consultas.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository repository;

    public ConsultaService(ConsultaRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Consulta salvar(Consulta consulta) {
        return repository.save(consulta);
    }

    // READ - LISTAR TODAS
    public List<Consulta> listarTodas() {
        return repository.findAll();
    }

    // READ - BUSCAR POR ID
    public Consulta buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
    }

    // UPDATE
    public Consulta atualizar(Long id, Consulta consultaAtualizada) {
        Consulta consulta = buscarPorId(id);

        consulta.setPaciente(consultaAtualizada.getPaciente());
        consulta.setMedico(consultaAtualizada.getMedico());
        consulta.setData(consultaAtualizada.getData());
        consulta.setHorario(consultaAtualizada.getHorario());
        consulta.setObservacao(consultaAtualizada.getObservacao());

        return repository.save(consulta);
    }

    // DELETE
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
