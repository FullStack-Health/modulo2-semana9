package br.com.exemplo.aula.controllers.dto;


public record LoginRequest(
        String username,
        String password,
        String nomePerfil
) {
}
