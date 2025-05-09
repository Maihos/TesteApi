package com.example.demo.Usuarios.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.UsuarioDto;
import com.example.demo.Mapper.UsuarioMapper;
import com.example.demo.Usuarios.Model.UsuarioModel;
import com.example.demo.Usuarios.Repository.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;
    
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioDto cadastrar(@Valid UsuarioDto usuarioDto) {
        UsuarioModel user = usuarioMapper.map(usuarioDto);
        user = usuarioRepository.save(user);
        if (usuarioRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email ja cadastrado");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        user.setSenha(user.getDataNascimento().format(formatter));
        return usuarioMapper.map(user);
    }

    public Optional<UsuarioModel> buscarPorId(long id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioDto atualizar(long id, UsuarioDto usuarioDto){
        Optional<UsuarioModel> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()){
            UsuarioModel atualiza = usuarioMapper.map(usuarioDto);
            atualiza.setId(id);
            UsuarioModel salvado = usuarioRepository.save(atualiza);
            return usuarioMapper.map(salvado); 
        }
        return null;
    }

    public void deletar(long id){
        usuarioRepository.deleteById(id);
    }
}
