package br.com.clinicapi.controller.response;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        String role
) {
}
