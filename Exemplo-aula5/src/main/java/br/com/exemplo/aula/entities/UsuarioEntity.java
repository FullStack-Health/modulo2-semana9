package br.com.exemplo.aula.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="usuario")
@Data
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usuario_id")
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    // Cascade desvincula um usuario ou perfil deletado da tabela many to many
    // Fetch Eager -> busca todos os perfis atrelados a um usuário sempre que buscar esse usuário
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_papel",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private Set<PerfilEntity> perfilEntityList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfilEntityList;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
