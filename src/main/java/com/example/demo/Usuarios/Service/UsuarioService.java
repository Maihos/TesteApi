package com.example.demo.Usuarios.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Usuarios.Model.UsuarioModel;
import com.example.demo.Usuarios.Repository.UsuarioRepository;
import jakarta.validation.Valid;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioModel cadastrar(@Valid UsuarioModel usuarioModel) {
        if (usuarioRepository.existsByEmail(usuarioModel.getEmail())) {
            throw new RuntimeException("Email ja cadastrado");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        usuarioModel.setSenha(usuarioModel.getDataNascimento().format(formatter));

        return usuarioRepository.save(usuarioModel);
    }

    public Optional<UsuarioModel> buscarPorId(long id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioModel atualizar(UsuarioModel usuarioModel){
        return usuarioRepository.save(usuarioModel);
    }

    public void deletar(long id){
        usuarioRepository.deleteById(id);
    }



}
