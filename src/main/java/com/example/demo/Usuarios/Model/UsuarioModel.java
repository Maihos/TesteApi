package com.example.demo.Usuarios.Model;

import java.time.LocalDate;

import org.hibernate.annotations.DialectOverride.Version;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tb_Cadastro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Definindo Estrategia de criaçao de tabela
    private Long id;

    @Version(major = 0)
    private Integer versao;

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

    @Column(name = "ativo")
    private boolean ativo = true;


}

