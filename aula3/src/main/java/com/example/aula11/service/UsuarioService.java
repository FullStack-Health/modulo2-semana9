package com.example.aula11.service;

import com.example.aula11.controller.dto.LoginRequest;
import com.example.aula11.entity.UsuarioEntity;
import com.example.aula11.infra.UsuarioUserDetails;
import com.example.aula11.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder;


    public Long salvaUsuario(LoginRequest cadastroRequest) {
        if(usuarioRepository.findByUsername(cadastroRequest.getUsername()).isPresent())
            throw new RuntimeException("Usuario " +cadastroRequest.getUsername()+ " já existe");

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsername(cadastroRequest.getUsername());
        usuario.setPassword(encoder.encode(cadastroRequest.getPassword()));

        return usuarioRepository.save(usuario).getId();

    }
}
