package br.com.futuroDev.APISustentavel.Model.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    @NotBlank(message = "O token não pode estar em branco.")
    private String token;
    @NotBlank(message = "O Username não pode estar em branco.")
    private String username;
}
