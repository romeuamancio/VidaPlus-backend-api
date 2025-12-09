package br.com.clinicapi.controller;

import br.com.clinicapi.controller.request.LoginRequest;
import br.com.clinicapi.controller.request.SignupRequest;
import br.com.clinicapi.controller.response.LoginResponse;
import br.com.clinicapi.controller.response.UsuarioResponse;
import br.com.clinicapi.domain.Usuario;
import br.com.clinicapi.service.TokenService;
import br.com.clinicapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioService usuarioService;

    @PostMapping("/signup")
    public ResponseEntity<UsuarioResponse> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(usuarioService.cadastrar(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        var authToken = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        var auth = authenticationManager.authenticate(authToken);
        var usuario = (Usuario) auth.getPrincipal();
        var jwt = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new LoginResponse(jwt));
    }
}
