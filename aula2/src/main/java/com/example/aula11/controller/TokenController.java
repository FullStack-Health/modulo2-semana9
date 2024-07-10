package com.example.aula11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final JwtEncoder encoder;
    private final long TEMPO_EXPIRACAO = 36000L;

    @PostMapping("/login")
    public String token(Authentication authentication){
        Instant agora = Instant.now();

        String escopo = authentication.getAuthorities()
                .stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.joining(" "));
        // busco todas as autoridade/papeis desse usuario,
        // juntar todas elas em uma unica string separada por espaço

        JwtClaimsSet claims = JwtClaimsSet.builder() //monta o payload do token jwt
                .issuer("self") //quem criou o token
                .issuedAt(agora) // momento da publicação do token
                .expiresAt(agora.plusSeconds(TEMPO_EXPIRACAO)) //momento de expiração, que é agora mais os segundos da variável TEMPO_EXPIRACAO
                .subject(authentication.getName()) //nome do usuário que está sendo autenticado
                .claim("escopo", escopo) //campo personalizado que recebe um string que contem todas as autoridades do usuário
                .build();

        // uso o encoder criado no SecurityConfig para realizar a criação do token JWT
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    }
}
