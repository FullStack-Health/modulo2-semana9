package com.example.aula11.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="funcionario")
@Data
@NoArgsConstructor
public class FuncionarioEntity {

    public FuncionarioEntity(String nome, Double salario, LocalDateTime dataAdmissao, String cargo) {
        this.nome = nome;
        this.salario = salario;
        this.dataAdmissao = dataAdmissao;
        this.cargo = cargo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double salario;
    private LocalDateTime dataAdmissao;
    private String cargo;


}
