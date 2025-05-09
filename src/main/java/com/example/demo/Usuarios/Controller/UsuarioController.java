package com.example.demo.Usuarios.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Usuarios.Model.UsuarioModel;
import com.example.demo.Usuarios.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api")


public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity <UsuarioModel> cadastrarUsuario (@Valid @RequestBody UsuarioModel usuarioModel) {
        UsuarioModel novoUsuarioModel = usuarioService.cadastrar(usuarioModel);
        return new ResponseEntity<>(novoUsuarioModel, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioModel> atualizarUsuarioModel (@PathVariable Long id,@Valid @RequestBody UsuarioModel usuarioAtualizado) {
        Optional<UsuarioModel> usuarioExistente = usuarioService.buscarPorId(id);

        if (usuarioExistente.isPresent()){
            usuarioAtualizado.setId(id);
        }
        
        return;
    }
}
