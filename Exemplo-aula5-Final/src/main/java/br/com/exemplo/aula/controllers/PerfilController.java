package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.controllers.dto.LoginRequest;
import br.com.exemplo.aula.controllers.dto.PerfilRequest;
import br.com.exemplo.aula.services.PerfilService;
import br.com.exemplo.aula.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;


    @PostMapping("/perfil")
    public ResponseEntity<String> cadastraPerfil(
            @RequestBody PerfilRequest perfilRequest
    ){
        perfilService.cadastraPerfil(perfilRequest);

        return new ResponseEntity<>("Perfil Criado",HttpStatus.CREATED);
    }
}
