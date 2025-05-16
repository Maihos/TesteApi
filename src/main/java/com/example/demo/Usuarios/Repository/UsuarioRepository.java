package com.example.demo.Usuarios.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Usuarios.Model.UsuarioModel;

import jakarta.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {


    boolean existsByEmail(String email); // Validando se o email ja existe no banco de dados
    List<UsuarioModel> findByAtivoTrue();
    Optional<UsuarioModel> findByIdAndAtivoTrue(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE UsuarioModel u SET u.ativo = false WHERE u.id = :id")
    void softDelete(@Param("id") Long id);
}
