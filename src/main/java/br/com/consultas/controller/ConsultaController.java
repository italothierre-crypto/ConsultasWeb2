package br.com.consultas.controller;

import br.com.consultas.dto.ApiResponse;
import br.com.consultas.model.Consulta;
import br.com.consultas.service.ConsultaService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<ApiResponse<Consulta>> salvar(@Valid @RequestBody Consulta consulta) {

        Consulta salva = service.salvar(consulta);

        ApiResponse<Consulta> response =
                new ApiResponse<>(true, "Consulta criada com sucesso", salva);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<Consulta> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public Consulta buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Consulta atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Consulta consulta) {

        return service.atualizar(id, consulta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Consulta removida com sucesso", null)
        );
    }
}