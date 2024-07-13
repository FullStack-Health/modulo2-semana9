package br.com.exemplo.aula.services;

import br.com.exemplo.aula.entities.Endereco;
import br.com.exemplo.aula.controllers.dto.EnderecoRequestDTO;
import br.com.exemplo.aula.controllers.dto.EnderecoResponseDTO;
import br.com.exemplo.aula.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<EnderecoResponseDTO> listarEnderecos() {
        return enderecoRepository.findAll().stream().map(
                endereco -> new EnderecoResponseDTO(
                        endereco.getId(),
                        endereco.getLogradouro(),
                        endereco.getNumero(),
                        endereco.getCidade(),
                        endereco.getEstado(),
                        endereco.getCep()
                )
        ).collect(Collectors.toList());
    }

    public EnderecoResponseDTO buscarEndereco(Long id){
        Endereco endereco = enderecoRepository.findById(id).orElse(null);
        if (endereco != null) {
            return new EnderecoResponseDTO(
                    endereco.getId(),
                    endereco.getLogradouro(),
                    endereco.getEstado(),
                    endereco.getCidade(),
                    endereco.getNumero(),
                    endereco.getCep()
            );
        }
        return null;
    }

    public EnderecoResponseDTO salvarEndereco(EnderecoRequestDTO request) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(request.getLogradouro());
        endereco.setNumero(request.getNumero());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());
        endereco.setCep(request.getCep());

        Endereco entitySalva = enderecoRepository.save(endereco);

        return new EnderecoResponseDTO(entitySalva.getId(),
                entitySalva.getLogradouro(),
                entitySalva.getNumero(),
                entitySalva.getCidade(),
                entitySalva.getEstado(),
                entitySalva.getCep()
        );
    }

    public EnderecoResponseDTO atualizarEndereco(Long id, EnderecoRequestDTO request) {
        Endereco endereco = enderecoRepository.findById(id).orElse(null);
        endereco.setLogradouro(request.getLogradouro());
        endereco.setNumero(request.getNumero());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());
        endereco.setCep(request.getCep());

        enderecoRepository.save(endereco);

        return new EnderecoResponseDTO(endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
    }

    public void removerEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }






}
