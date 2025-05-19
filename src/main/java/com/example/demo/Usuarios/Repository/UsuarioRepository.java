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


    boolean existsByEmail(String email);
    
    List<UsuarioModel> findByAtivoTrue();
    Optional<UsuarioModel> findByIdAndAtivoTrue(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE UsuarioModel u SET u.ativo = false WHERE u.id = :id AND u.ativo = true")
    int softDeleteAtivo(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE UsuarioModel u SET " +
            "u.nome = CASE WHEN :nome IS NOT NULL THEN :nome ELSE u.nome END, " +
            "u.senha = CASE WHEN :senha IS NOT NULL THEN :senha ELSE u.senha END " +
            "WHERE u.id = :id AND u.ativo = true")
    int atualizarDireto(@Param("id") Long id, @Param("nome") String nome, @Param("senha") String senha);
}
    

