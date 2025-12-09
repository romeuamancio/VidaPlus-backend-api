package br.com.clinicapi.service;

import br.com.clinicapi.controller.request.PacienteRequest;
import br.com.clinicapi.controller.response.PacienteResponse;
import br.com.clinicapi.domain.Paciente;
import br.com.clinicapi.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteResponse criar(PacienteRequest request) {
        var paciente = new Paciente(
                request.nome(),
                request.cpf(),
                request.dataNascimento(),
                request.telefone(),
                request.email()
        );
        var salvo = pacienteRepository.save(paciente);
        return toResponse(salvo);
    }

    public List<PacienteResponse> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PacienteResponse buscarPorId(Long id) {
        var paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        return toResponse(paciente);
    }

    public PacienteResponse atualizar(Long id, PacienteRequest request) {
        var paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        paciente.setNome(request.nome());
        paciente.setCpf(request.cpf());
        paciente.setDataNascimento(request.dataNascimento());
        paciente.setTelefone(request.telefone());
        paciente.setEmail(request.email());

        var atualizado = pacienteRepository.save(paciente);
        return toResponse(atualizado);
    }

    public void deletar(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado");
        }
        pacienteRepository.deleteById(id);
    }

    private PacienteResponse toResponse(Paciente paciente) {
        return new PacienteResponse(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getDataNascimento(),
                paciente.getTelefone(),
                paciente.getEmail()
        );
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("admin123"));
    }
}
