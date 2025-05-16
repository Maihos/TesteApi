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

import jakarta.transaction.Transactional;
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
    @Transactional
    public boolean atualizarSenha(long id, String novaSenha) {
        Optional<UsuarioModel> usuarioExistenteOptional = usuarioRepository.findById(id);

        if (usuarioExistenteOptional.isPresent()) {
            UsuarioModel usuarioExistente = usuarioExistenteOptional.get();
            usuarioExistente.setSenha(novaSenha);
            usuarioRepository.save(usuarioExistente);
            return true;
        }
        return false;
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

    @Transactional
    public boolean softDelete(long id) {
        int linhasAfetadas = usuarioRepository.softDeleteAtivo(id);
        return linhasAfetadas > 0;
    }
    

    public List<UsuarioDto> lista(){
        List<UsuarioModel> user = usuarioRepository.findByAtivoTrue();
        return user.stream()
        .map(usuarioMapper::map)
        .collect(Collectors.toList());
    }
    
    public UsuarioDto listaIdAtivo(long id){
        Optional<UsuarioModel> listarId = usuarioRepository.findByIdAndAtivoTrue(id);
        return listarId.map(usuarioMapper::map).orElse(null);
    }

}
