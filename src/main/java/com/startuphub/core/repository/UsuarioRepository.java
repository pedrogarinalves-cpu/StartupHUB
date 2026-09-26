package com.startuphub.core.repository;

import com.startuphub.core.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {


    Optional<Usuario> findByEmail(String email);
}
