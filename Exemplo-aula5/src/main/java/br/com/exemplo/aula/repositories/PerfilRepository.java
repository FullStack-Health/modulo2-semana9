package br.com.exemplo.aula.repositories;

import br.com.exemplo.aula.entities.PerfilEntity;
import br.com.exemplo.aula.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {
    Optional<PerfilEntity> findByNomePerfil(String nomePerfil);
}

