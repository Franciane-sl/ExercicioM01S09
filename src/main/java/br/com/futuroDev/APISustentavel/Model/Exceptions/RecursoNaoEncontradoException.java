package br.com.futuroDev.APISustentavel.Model.Exceptions;

public class RecursoNaoEncontradoException extends RuntimeException{

    public RecursoNaoEncontradoException(String messagem){
        super(messagem);
    }
}
