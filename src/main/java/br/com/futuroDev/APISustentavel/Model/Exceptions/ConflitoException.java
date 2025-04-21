package br.com.futuroDev.APISustentavel.Model.Exceptions;

public class ConflitoException extends RuntimeException{

    public ConflitoException(String message){
        super(message);
    }
}
