package br.com.futuroDev.APISustentavel.Model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<RespostaDeErro> handlerRecursoNaoEncontradoException(RecursoNaoEncontradoException e){
        RespostaDeErro error = new RespostaDeErro(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SolicitacaoIncorretaException.class)
    public ResponseEntity<RespostaDeErro> handlerSolicitacaoIncorretaException(SolicitacaoIncorretaException e){
        RespostaDeErro error = new RespostaDeErro(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflitoException.class)
    public ResponseEntity<RespostaDeErro> handlerConflitoException (ConflitoException e) {
        RespostaDeErro error = new RespostaDeErro(HttpStatus.CONFLICT.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<RespostaDeErro> handlerValidacaoException (ValidacaoException e) {
        RespostaDeErro error = new RespostaDeErro(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaDeErro> handlerGenericException(Exception e) {
        RespostaDeErro error = new RespostaDeErro(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespostaDeErro> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder mensagens = new StringBuilder();

        e.getBindingResult().getFieldErrors().forEach(erro -> {
            mensagens.append(erro.getField())
                    .append(": ")
                    .append(erro.getDefaultMessage())
                    .append("; ");
        });

        RespostaDeErro error = new RespostaDeErro(HttpStatus.BAD_REQUEST.value(), mensagens.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



}
