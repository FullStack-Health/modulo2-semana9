package br.com.exemplo.aula.services;

import br.com.exemplo.aula.controllers.dto.LoginRequest;
import br.com.exemplo.aula.entities.PerfilEntity;
import br.com.exemplo.aula.entities.UsuarioEntity;
import br.com.exemplo.aula.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final PerfilService perfilService;

    public UsuarioEntity validaUsuario(
            LoginRequest loginRequest
    ){
        UsuarioEntity usuarioEntity = usuarioRepository
                .findByUsername(loginRequest.username())
                .orElseThrow(
                        ()->new RuntimeException("Usuário não existe com o nome "
                                +loginRequest.username())
                );
        if(!passwordEncoder.matches(loginRequest.password(),usuarioEntity.getPassword())){
            throw new RuntimeException("Senha errada para usuario com nome "
                    + loginRequest.username());
        }
        return usuarioEntity;
    }

    public void cadastraUsuario(LoginRequest cadastroRequest) {
        if (usuarioRepository.findByUsername(cadastroRequest.username())
                        .isPresent()){
            throw new RuntimeException("Usuário existe com nome: "+cadastroRequest.username());
        }
        PerfilEntity perfilEntity = perfilService.validaPerfil(cadastroRequest.nomePerfil());

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setUsername(cadastroRequest.username());
        usuarioEntity.setPassword(passwordEncoder.encode(cadastroRequest.password()));
        usuarioEntity.setPerfilEntityList(Set.of(perfilEntity));

        usuarioRepository.save(usuarioEntity);
    }
}
