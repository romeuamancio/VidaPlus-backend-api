package br.com.clinicapi.service;

import br.com.clinicapi.controller.request.LoginRequest;
import br.com.clinicapi.controller.request.SignupRequest;
import br.com.clinicapi.controller.response.UsuarioResponse;
import br.com.clinicapi.domain.Usuario;
import br.com.clinicapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioResponse cadastrar(SignupRequest request) {
        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        String senhaCodificada = passwordEncoder.encode(request.senha());

        var usuario = new Usuario(
                request.nome(),
                request.email(),
                senhaCodificada,
                "ROLE_USER"
        );

        Usuario salvo = usuarioRepository.save(usuario);

        return new UsuarioResponse(salvo.getId(), salvo.getNome(), salvo.getEmail(), salvo.getRole());
    }
}
