package com.example.aula11.controller;

import com.example.aula11.controller.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final JwtEncoder encoder;
    private final long TEMPO_EXPIRACAO = 36000L;

    @PostMapping("/login")
    // o authentication não está sendo preenchido pela aplicação, siga para a próxima aula
    public String token(
            @RequestBody LoginRequest loginRequest
    ){
        Instant agora = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("backend1")
                .issuedAt(agora)
                .expiresAt(agora.plusSeconds(TEMPO_EXPIRACAO))
                .subject(loginRequest.getUsername())
                .claim("escopo", "admin")
                .build();

        // uso o encoder criado no SecurityConfig para realizar a criação do token JWT
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    }
}
