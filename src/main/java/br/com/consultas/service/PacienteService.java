package br.com.consultas.service;

import br.com.consultas.model.Paciente;
import br.com.consultas.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Paciente salvar(Paciente paciente) {
        return repository.save(paciente);
    }

    // READ - LISTAR TODOS
    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    // READ - BUSCAR POR ID
    public Paciente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    // UPDATE
    public Paciente atualizar(Long id, Paciente pacienteAtualizado) {
        Paciente paciente = buscarPorId(id);

        paciente.setNome(pacienteAtualizado.getNome());
        paciente.setCpf(pacienteAtualizado.getCpf());
        paciente.setTelefone(pacienteAtualizado.getTelefone());
        paciente.setEmail(pacienteAtualizado.getEmail());
        paciente.setDataNascimento(pacienteAtualizado.getDataNascimento());

        return repository.save(paciente);
    }

    // DELETE
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
