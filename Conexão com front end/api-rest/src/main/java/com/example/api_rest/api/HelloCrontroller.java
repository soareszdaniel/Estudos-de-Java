package com.example.api_rest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indica que esta classe é um Controller REST
@RequestMapping("/api") // Define o prefixo da URL para todas as rotas deste Controller
public class HelloCrontroller {
    @GetMapping("/hello") // Mapeia a rota GET /api/hello
    public String hello(@RequestParam String name) {
        return "Olá, " + name + "! Bem-vindo à API REST!";
    }
}
