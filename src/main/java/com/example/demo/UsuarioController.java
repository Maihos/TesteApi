package com.example.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping


public class UsuarioController {
    @GetMapping("/noasvindas")
    @PostMapping
    @PutMapping
    @PatchMapping
    @DeleteMapping

    public String boasVindas(){
        return "primeira mensagem";
    }



}
