package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.services.ConsultaService;
import br.com.exemplo.aula.controllers.dto.ConsultaRequestDTO;
import br.com.exemplo.aula.controllers.dto.ConsultaResponseDTO;
import br.com.exemplo.aula.controllers.dto.ConsultaResponseListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping()
    public ConsultaResponseDTO salvarConsulta(@RequestBody ConsultaRequestDTO request) {
        return consultaService.salvarConsulta(request);
    }

    @GetMapping()
    public List<ConsultaResponseListDTO> listarConsultas() {
        var consultas = consultaService.listarConsultas();
        if (consultas.isEmpty()){
            return null;
        } else {
            return consultas;
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> search(@PathVariable long id) {
        ConsultaResponseDTO response = consultaService.buscarConsulta(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            throw new RuntimeException("404");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable long id) {
        consultaService.removerConsulta(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> update(@PathVariable long id, @RequestBody ConsultaRequestDTO request) {
        ConsultaResponseDTO consulta = consultaService.atualizarConsulta(id, request);
        if (consulta != null) {
            return ResponseEntity.ok(consulta);
        } else {
            throw new RuntimeException("404");
        }
    }
    
}
