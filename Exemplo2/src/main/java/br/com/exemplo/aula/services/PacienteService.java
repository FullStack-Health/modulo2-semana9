package br.com.exemplo.aula.services;

import br.com.exemplo.aula.entities.Paciente;
import br.com.exemplo.aula.controllers.dto.PacienteRequestDTO;
import br.com.exemplo.aula.controllers.dto.PacienteResponseDTO;
import br.com.exemplo.aula.repositories.EnderecoRepository;
import br.com.exemplo.aula.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final EnderecoRepository enderecoRepository;


    public PacienteService(PacienteRepository pacienteRepository, EnderecoRepository enderecoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public List<PacienteResponseDTO> listarPacientes() {
        return pacienteRepository.findAll().stream().map(
                paciente -> new PacienteResponseDTO(
                        paciente.getId(),
                        paciente.getNome(),
                        paciente.getDataNascimento(),
                        paciente.getCpf(),
                        paciente.getTelefone(),
                        paciente.getEmail(),
                        paciente.getEndereco()
                )
        ).collect(Collectors.toList());
    }

    public PacienteResponseDTO buscarPaciente(Long id){
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if (paciente != null) {
            return new PacienteResponseDTO(
                    paciente.getId(),
                    paciente.getNome(),
                    paciente.getDataNascimento(),
                    paciente.getCpf(),
                    paciente.getTelefone(),
                    paciente.getEmail(),
                    paciente.getEndereco()
            );
        }
        return null;
    }

    public PacienteResponseDTO salvarPaciente(PacienteRequestDTO request) {
        Paciente paciente = mapearRequest(request);
        Paciente entitySalva = pacienteRepository.save(paciente);

        return new PacienteResponseDTO(entitySalva.getId(),
                entitySalva.getNome(),
                entitySalva.getDataNascimento(),
                entitySalva.getCpf(),
                entitySalva.getTelefone(),
                entitySalva.getEmail(),
                entitySalva.getEndereco()
        );
    }

    private Paciente mapearRequest(PacienteRequestDTO source){
        Paciente target = new Paciente();
        target.setNome(source.getNome());
        target.setDataNascimento(source.getDataNascimento());
        target.setCpf(source.getCpf());
        target.setTelefone(source.getTelefone());
        target.setEmail(source.getEmail());
        target.setEndereco(enderecoRepository.findById(source.getIdEndereco()).orElse(null));
        return target;
    }

    public PacienteResponseDTO atualizarPaciente(Long id, PacienteRequestDTO request) {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        assert paciente != null;
        paciente.setNome(request.getNome());
        paciente.setDataNascimento(request.getDataNascimento());
        paciente.setCpf(request.getCpf());
        paciente.setTelefone(request.getTelefone());
        paciente.setEmail(request.getEmail());
        paciente.setEndereco(enderecoRepository.findById(request.getIdEndereco()).orElse(null));

        pacienteRepository.save(paciente);

        return new PacienteResponseDTO(paciente.getId(),
                paciente.getNome(),
                paciente.getDataNascimento(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEmail(),
                paciente.getEndereco()
        );
    }

    public void removerPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
    
    
}
