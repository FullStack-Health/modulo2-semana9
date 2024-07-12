package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.controllers.dto.EnderecoRequestDTO;
import br.com.exemplo.aula.services.EnderecoService;
import br.com.exemplo.aula.controllers.dto.EnderecoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping()
    public EnderecoResponseDTO salvarEndereco(@RequestBody EnderecoRequestDTO request) {
        return enderecoService.salvarEndereco(request);
    }

    @GetMapping()
    public List<EnderecoResponseDTO> listarEnderecos() {
        var enderecos = enderecoService.listarEnderecos();
        if (enderecos.isEmpty()){
            return null;
        } else {
            return enderecos;
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> search(@PathVariable long id) {
        EnderecoResponseDTO response = enderecoService.buscarEndereco(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            throw new RuntimeException("404");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable long id) {
         enderecoService.removerEndereco(id);
         return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> update(@PathVariable long id, @RequestBody EnderecoRequestDTO request) {
        EnderecoResponseDTO endereco = enderecoService.atualizarEndereco(id, request);
        if (endereco != null) {
            return ResponseEntity.ok(endereco);
        } else {
            throw new RuntimeException("404");
        }
    }



}
