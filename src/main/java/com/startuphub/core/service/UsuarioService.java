package com.startuphub.core.service;

import com.startuphub.core.model.Usuario;
import com.startuphub.core.repository.UsuarioRepository;
import com.startuphub.core.model.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario Cadastrar(String nome, String email, String senhaTextoPuro, Role role){
        String senhaHash = passwordEncoder.encode(senhaTextoPuro);
        Usuario usuario = new Usuario(nome, email, senhaHash, role);
        return usuarioRepository.save(usuario);
    }
}
