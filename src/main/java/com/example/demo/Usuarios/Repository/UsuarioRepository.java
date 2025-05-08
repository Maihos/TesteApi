package com.example.demo.Usuarios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Usuarios.Model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {


    boolean existsByEmail(String email); // Validando se o email ja existe no banco de dados
}
