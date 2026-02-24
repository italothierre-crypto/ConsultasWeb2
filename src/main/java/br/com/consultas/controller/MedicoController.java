package br.com.consultas.controller;

import br.com.consultas.model.Medico;
import br.com.consultas.service.MedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Medico> criar(@RequestBody Medico medico) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.salvar(medico));
    }

    @GetMapping
    public List<Medico> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Medico buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Medico atualizar(
            @PathVariable Long id,
            @RequestBody Medico medico) {

        return service.atualizar(id, medico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
