package br.com.futuroDev.APISustentavel.Controller;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @GetMapping("/admin")
    public String getAdmin(){
        return "Admin acessado com sucesso.";
    }

    @GetMapping("/user")
    public String getUser(){
        return "User acessado com sucesso";
    }

    @GetMapping("/public")
    public String getPublic(){
        return "Acesso publico liberado com sucesso";
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }


    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendError(HttpStatus.FORBIDDEN.value(), "Erro 403: Você não tem permissão para acessar este recurso.");
        };
    }
}
