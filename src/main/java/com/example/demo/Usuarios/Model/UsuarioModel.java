package com.example.demo.Usuarios.Model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
public class UsuarioModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Definindo Estrategia de criaçao de tabela
    private Long id;

    @Column
    @NotBlank(message = "Nome obrigatorio") // validacao
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @Column
    @NotBlank(message = "Email Obrigatorio")
    @Email(message = "Email Invalido")
    private String email;

    private String senha;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  LocalDate dataNascimento;


}

