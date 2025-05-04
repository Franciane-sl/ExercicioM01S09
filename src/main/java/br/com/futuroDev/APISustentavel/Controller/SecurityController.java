package br.com.futuroDev.APISustentavel.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/admin")
    public String getAdmin(){
        return "Admin acessado com sucesso.";
    }

    @GetMapping("/user")
    public String getUser(){
        return "User acessado com sucesso.";
    }

    @GetMapping("/public")
    public String getPublic(){
        return "Public acessado com sucesso.";
    }
}
