package com.example.aula11.controller;

import com.example.aula11.controller.dto.FuncionarioRequest;
import com.example.aula11.controller.dto.FuncionarioResponse;
import com.example.aula11.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService service;


    @GetMapping
    public ResponseEntity<List<FuncionarioResponse>> listarFuncionarios(){
        List<FuncionarioResponse> response = service.listaFuncionarios();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponse> salvarFuncionarios(
            @RequestBody FuncionarioRequest request
            ){
        FuncionarioResponse response = service.salvaFuncionario(request);

        return ResponseEntity.ok(response);
    }
}
