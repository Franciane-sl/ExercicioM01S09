package br.com.futuroDev.APISustentavel.Model.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotBlank(message = "O nome não pode estar em branco.")
    private String name;
    @NotBlank(message = "A senha não pode estar em branco.")
    private String password;
}
