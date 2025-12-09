package br.com.clinicapi.controller.request;

public record SignupRequest(
        String nome,
        String email,
        String senha
) {
}
