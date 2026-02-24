package br.com.consultas.service;

import br.com.consultas.model.Medico;
import br.com.consultas.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public Medico salvar(Medico medico) {
        return repository.save(medico);
    }

    public List<Medico> listarTodos() {
        return repository.findAll();
    }

    public Medico buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
    }

    public Medico atualizar(Long id, Medico medicoAtualizado) {
        Medico medico = buscarPorId(id);

        medico.setNome(medicoAtualizado.getNome());
        medico.setEspecialidade(medicoAtualizado.getEspecialidade());
        medico.setCrm(medicoAtualizado.getCrm());
        medico.setTelefone(medicoAtualizado.getTelefone());
        medico.setEmail(medicoAtualizado.getEmail());

        return repository.save(medico);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
