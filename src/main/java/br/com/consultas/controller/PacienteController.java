package br.com.consultas.controller;

import br.com.consultas.model.Paciente;
import br.com.consultas.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Paciente> criar(@RequestBody Paciente paciente) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.salvar(paciente));
    }

    // READ - LISTAR TODOS
    @GetMapping
    public List<Paciente> listar() {
        return service.listarTodos();
    }

    // READ - BUSCAR POR ID
    @GetMapping("/{id}")
    public Paciente buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Paciente atualizar(
            @PathVariable Long id,
            @RequestBody Paciente paciente) {

        return service.atualizar(id, paciente);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
