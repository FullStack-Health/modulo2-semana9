package com.example.aula11.service;

import com.example.aula11.controller.dto.FuncionarioRequest;
import com.example.aula11.controller.dto.FuncionarioResponse;
import com.example.aula11.entity.FuncionarioEntity;
import com.example.aula11.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public List<FuncionarioResponse> listaFuncionarios() {
        return repository.findAll()
                .stream().map(funcionarioEntity -> new FuncionarioResponse(funcionarioEntity))
                .collect(Collectors.toList());
    }

    public FuncionarioResponse salvaFuncionario(FuncionarioRequest request) {

        FuncionarioEntity entity = new FuncionarioEntity(
                request.getNome(),
                request.getSalario(),
                request.getDataAdmissao(),
                request.getCargo()
        );

        FuncionarioResponse response = new FuncionarioResponse(repository.save(entity));
        return response;
    }

}
