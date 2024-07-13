package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.controllers.dto.LoginRequest;
import br.com.exemplo.aula.controllers.dto.PerfilRequest;
import br.com.exemplo.aula.services.PerfilService;
import br.com.exemplo.aula.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PerfilService perfilService;


    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastraUsuario(
            @RequestBody LoginRequest cadastroRequest
    ){
        usuarioService.cadastraUsuario(cadastroRequest);

        return new ResponseEntity<>("Usuario Criado",HttpStatus.CREATED);
    }
}
