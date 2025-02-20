package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

    @GetMapping
    public String texto () {
        return "Acessando a api";
    }
    
}
