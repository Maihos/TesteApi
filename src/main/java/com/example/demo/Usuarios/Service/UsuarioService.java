package com.example.demo.Usuarios.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Usuarios.Dto.UsuarioDto;
import com.example.demo.Usuarios.Mapper.UsuarioMapper;
import com.example.demo.Usuarios.Model.UsuarioModel;
import com.example.demo.Usuarios.Repository.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioDto cadastrar(@Valid UsuarioDto usuarioDto) {
        if (usuarioRepository.existsByEmail(usuarioDto.getEmail())) {
            throw new RuntimeException("Email ja cadastrado");
        }
        UsuarioModel user = usuarioMapper.map(usuarioDto);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        user.setSenha(user.getDataNascimento().format(formatter));
        
        user = usuarioRepository.save(user);
        return usuarioMapper.map(user);
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

    public List<UsuarioDto> lista(){
        List<UsuarioModel> user = usuarioRepository.findAll();
        return user.stream()
        .map(usuarioMapper::map)
        .collect(Collectors.toList());
    }
    
    public UsuarioDto listaId(long id){
        Optional<UsuarioModel> listarId = usuarioRepository.findById(id);
        return listarId.map(usuarioMapper::map).orElse(null);
    }

}
