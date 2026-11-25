package com.devsuperior.bds02.excecao;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExeptionHendler {



    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<StandardException> resourceNotFoundException(ResouceNotFoundException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardException err = new StandardException();

        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Id nao Encontrado");
        err.setMenssagem(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(err);


    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardException> databaseException(DatabaseException e , HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardException err = new StandardException();

        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Error de integridade");
        err.setMenssagem(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

}
