package br.com.exemplo.aula.controllers.dto;

import br.com.exemplo.aula.entities.Endereco;

public class NutricionistaResponseDTO {

    private Long id;
    private String nome;
    private String matricula;
    private int tempoExperiencia;
    private Endereco endereco;
    private String crn;
    private String especialidade;

    public NutricionistaResponseDTO() {
    }

    public NutricionistaResponseDTO(Long id, String nome, String matricula, int tempoExperiencia, Endereco endereco, String crn, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.tempoExperiencia = tempoExperiencia;
        this.endereco = endereco;
        this.crn = crn;
        this.especialidade = especialidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getTempoExperiencia() {
        return tempoExperiencia;
    }

    public void setTempoExperiencia(int tempoExperiencia) {
        this.tempoExperiencia = tempoExperiencia;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}