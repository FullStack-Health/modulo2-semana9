package com.example.aula11.controller;

import com.example.aula11.controller.dto.LoginRequest;
import com.example.aula11.infra.UsuarioUserDetails;
import com.example.aula11.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    // o authentication não está sendo preenchido pela aplicação, siga para a próxima aula
    public String token(
            @RequestBody LoginRequest loginRequest
    ){

        // gera uma autenticação do Spring Security
        Authentication authentication = authenticationManager.
                authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()
                        )
                );

        // valida a autenticação com o usuario e senha informado anteriormente
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // retorno o usuário autenticado
        UsuarioUserDetails usuarioUserDetails = (UsuarioUserDetails) authentication.getPrincipal();

        String token = tokenService.geraToken(authentication);
        return token;
    }
}
