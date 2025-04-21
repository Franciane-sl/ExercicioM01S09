package br.com.futuroDev.APISustentavel.Model.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RespostaDeErro {

    private int status;
    private String message;
}
