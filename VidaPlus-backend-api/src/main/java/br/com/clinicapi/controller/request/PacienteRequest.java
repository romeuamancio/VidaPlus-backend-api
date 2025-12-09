package br.com.clinicapi.controller.request;

import java.time.LocalDate;

public record PacienteRequest(
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String telefone,
        String email
) {
}
