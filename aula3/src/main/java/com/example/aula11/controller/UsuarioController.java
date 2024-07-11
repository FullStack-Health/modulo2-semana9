package com.example.aula11.controller;

import com.example.aula11.controller.dto.LoginRequest;
import com.example.aula11.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public Long criaUsuario(
            @RequestBody LoginRequest cadastroRequest
    ) {
        return usuarioService.salvaUsuario(cadastroRequest);
    }

}
