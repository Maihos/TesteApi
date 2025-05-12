package com.example.demo.Usuarios.Mapper;

import org.springframework.stereotype.Component;

import com.example.demo.Usuarios.Dto.UsuarioDto;
import com.example.demo.Usuarios.Model.UsuarioModel;

@Component
public class UsuarioMapper {

    public UsuarioModel map(UsuarioDto usuarioDto){

        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(usuarioDto.getId());
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(usuarioDto.getSenha());
        usuario.setDataNascimento(usuarioDto.getDataNascimento());

        return usuario;
        

    }

    public UsuarioDto map(UsuarioModel usuario){

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setSenha(usuario.getSenha());
        usuarioDto.setDataNascimento(usuario.getDataNascimento());

        return usuarioDto;


    }

}
