package br.com.consultas.service;

import br.com.consultas.model.Consulta;
import br.com.consultas.model.Paciente;
import br.com.consultas.model.Medico;
import br.com.consultas.repository.ConsultaRepository;
import br.com.consultas.repository.PacienteRepository;
import br.com.consultas.repository.MedicoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ConsultaService {

    private final ConsultaRepository repository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaService(ConsultaRepository repository,
                           PacienteRepository pacienteRepository,
                           MedicoRepository medicoRepository) {
        this.repository = repository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    // ==============================
    // CREATE
    // ==============================

    public Consulta salvar(Consulta consulta) {

        Paciente paciente = pacienteRepository.findById(
                consulta.getPaciente().getId()
        ).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Medico medico = medicoRepository.findById(
                consulta.getMedico().getId()
        ).orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        consulta.setPaciente(paciente);
        consulta.setMedico(medico);

        return repository.save(consulta);
    }

    // ==============================
    // READ
    // ==============================

    public List<Consulta> listarTodas() {
        return repository.findAll();
    }

    public Consulta buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
    }

    // ==============================
    // UPDATE TOTAL (PUT)
    // ==============================

    public Consulta atualizar(Long id, Consulta consultaAtualizada) {

        Consulta consulta = buscarPorId(id);

        Paciente paciente = pacienteRepository.findById(
                consultaAtualizada.getPaciente().getId()
        ).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Medico medico = medicoRepository.findById(
                consultaAtualizada.getMedico().getId()
        ).orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setData(consultaAtualizada.getData());
        consulta.setHorario(consultaAtualizada.getHorario());
        consulta.setObservacao(consultaAtualizada.getObservacao());

        return repository.save(consulta);
    }

    // ==============================
    // UPDATE PARCIAL (PATCH)
    // ==============================

    public Consulta atualizarParcial(Long id, Map<String, Object> campos) {

        Consulta consulta = buscarPorId(id);

        campos.forEach((campo, valor) -> {

            switch (campo) {

                case "data":
                    consulta.setData(LocalDate.parse((String) valor));
                    break;

                case "horario":
                    consulta.setHorario(LocalTime.parse((String) valor));
                    break;

                case "observacao":
                    consulta.setObservacao((String) valor);
                    break;

                case "paciente":
                    Map<String, Object> pacienteMap = (Map<String, Object>) valor;
                    Long pacienteId = Long.valueOf(pacienteMap.get("id").toString());

                    Paciente paciente = pacienteRepository.findById(pacienteId)
                            .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

                    consulta.setPaciente(paciente);
                    break;

                case "medico":
                    Map<String, Object> medicoMap = (Map<String, Object>) valor;
                    Long medicoId = Long.valueOf(medicoMap.get("id").toString());

                    Medico medico = medicoRepository.findById(medicoId)
                            .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

                    consulta.setMedico(medico);
                    break;

                default:
                    throw new RuntimeException("Campo inválido: " + campo);
            }
        });

        return repository.save(consulta);
    }

    // ==============================
    // DELETE
    // ==============================

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Consulta não encontrada");
        }
        repository.deleteById(id);
    }
}