package com.startuphub.api.controller;
import com.startuphub.api.dto.LoginRequest;
import com.startuphub.api.dto.RegisterRequest;
import com.startuphub.api.dto.TokenResponse;
import com.startuphub.api.dto.UsuarioResponse;
import com.startuphub.api.security.JwtService;
import com.startuphub.core.model.Role;
import com.startuphub.core.model.Usuario;
import com.startuphub.core.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(UsuarioService usuarioService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
@PostMapping("/register")
public UsuarioResponse register(@Valid @RequestBody RegisterRequest request) {
        Usuario usuario = usuarioService.Cadastrar( request.getNome(), request.getEmail(), request.getSenha(), Role.MEMBRO);
    return new UsuarioResponse(
            usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getRole().name());
}
@PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest request ){
    Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha()));
    UserDetails userDetails = (UserDetails) auth.getPrincipal();
    return new TokenResponse(jwtService.gerarToken(userDetails));
}

}
