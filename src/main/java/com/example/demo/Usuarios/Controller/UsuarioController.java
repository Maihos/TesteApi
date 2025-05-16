package com.example.demo.Usuarios.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Usuarios.Dto.UsuarioDto;
import com.example.demo.Usuarios.Service.UsuarioService;

import jakarta.validation.Valid;





@RestController
@RequestMapping("/api")


public class UsuarioController {
    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController (UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar (@Valid @RequestBody UsuarioDto user) {
        UsuarioDto novoUsuario = usuarioService.cadastrar(user);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body("usuario criado com sucesso: "+ novoUsuario.getNome() + "(id): " + novoUsuario.getId());
    }
    @PatchMapping("/senha/{id}")
    public ResponseEntity<String> alterarSenha(@PathVariable Long id, @RequestBody String novaSenha) {
        if (usuarioService.atualizarSenha(id, novaSenha)) {
            return ResponseEntity.ok("Senha do usuario com id: " + id + " alterada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Id do usuario nao encontrado");
        }
    }
    @GetMapping("/lista")
    public ResponseEntity <List<UsuarioDto>> lista() {
        List<UsuarioDto> usuario = usuarioService.lista();
        return ResponseEntity.ok(usuario);
    }
    @GetMapping("/lista/{id}")
    public ResponseEntity<?> listaId(@PathVariable Long id) {
        UsuarioDto usuario = usuarioService.listaIdAtivo(id);
        if (usuario !=null){
            return ResponseEntity.ok(usuario);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Id inválido");
        }
        
    }
    @GetMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar (@PathVariable Long id, @RequestBody UsuarioDto userAtualizado){
        UsuarioDto user = usuarioService.atualizar(id, userAtualizado);
        if (user != null){
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Id do usuario inexistente");
        }
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> softDeleteUsuario(@PathVariable Long id) {
        if (usuarioService.listaIdAtivo(id) != null) { 
            usuarioService.softDelete(id);
            return ResponseEntity.ok("Usuario com id: " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("id do usuario nao encontrado ou usuario ja inativo");
        }
    }
    
}
