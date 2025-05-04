package br.com.futuroDev.APISustentavel.Model.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotBlank(message = "O nome não pode ser nulo.")
    private String name;
    @NotBlank(message = "A senha não pode ser nula.")
    private String password;
}
