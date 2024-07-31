package br.com.exemplo.aula.controllers.dto;

public record LoginResponse(String token, Long tempoExpiracao) {
}
