package com.example.demo.Usuarios.Dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;

}
