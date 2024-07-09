package com.example.aula11.controller.dto;

import com.example.aula11.entity.FuncionarioEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FuncionarioResponse {

    public FuncionarioResponse(FuncionarioEntity entity) {
        this.nome = entity.getNome();
        this.salario = entity.getSalario();
        this.dataAdmissao = entity.getDataAdmissao();
        this.cargo = entity.getCargo();
    }

    private String nome;
    private Double salario;

    @JsonSerialize
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataAdmissao;
    private String cargo;


}
