package com.example.demo.Usuarios.Controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Usuarios.Model.UsuarioModel;
import com.example.demo.Usuarios.Service.UsuarioService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/usuarios")


public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity <UsuarioModel> cadastrarUsuario (@Valid @RequestBody UsuarioModel usuarioModel) {
        try{
            UsuarioModel novoUsuarioModel = usuarioService.cadastrar(usuarioModel);
            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "Usuário cadastrado com sucesso.");
            return new ResponseEntity<>(novoUsuarioModel, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
