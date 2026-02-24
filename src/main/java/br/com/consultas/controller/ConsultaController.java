package br.com.consultas.controller;

import br.com.consultas.model.Consulta;
import br.com.consultas.service.ConsultaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Consulta> criar(@RequestBody Consulta consulta) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.salvar(consulta));
    }

    // READ - LISTAR TODAS
    @GetMapping
    public List<Consulta> listar() {
        return service.listarTodas();
    }

    // READ - BUSCAR POR ID
    @GetMapping("/{id}")
    public Consulta buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Consulta atualizar(
            @PathVariable Long id,
            @RequestBody Consulta consulta) {

        return service.atualizar(id, consulta);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
