package com.example.aula11.infra;

import com.example.aula11.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
//cria uma implentação para o UserDetails com base no UsuarioEntity
// o UserDetails é usado para validar usuários dentro do spring security
public class UsuarioUserDetails extends UsuarioEntity implements UserDetails {
   
    private final UsuarioEntity user;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

}
