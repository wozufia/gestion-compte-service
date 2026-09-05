package com.awa.centrale.gestioncompte.exception;

import com.awa.centrale.gestioncompte.model.Erreur;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Erreur> handleEntityNotFound(EntityNotFoundException exception) {
        Erreur erreur = buildError("NOT_FOUND", HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erreur);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Erreur> handleIllegalArgument(IllegalArgumentException exception) {
        Erreur erreur = buildError("BAD_REQUEST", HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erreur);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Erreur> handleGenericException(Exception exception) {
        Erreur erreur = buildError("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erreur);
    }

    private Erreur buildError(String status, Integer httpStatus, String message) {
        Erreur erreur = new Erreur();
        erreur.setStatus(status);
        erreur.setHttpStatus(httpStatus);
        erreur.setMessage(message);
        return erreur;
    }
}
