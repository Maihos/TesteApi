package com.example.demo.Usuarios.Dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UsuarioDto {

    private long id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    
    public UsuarioDto(long id, String nome, String email, String senha, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

    

}
