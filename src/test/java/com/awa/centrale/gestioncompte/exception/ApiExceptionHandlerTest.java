package com.awa.centrale.gestioncompte.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.awa.centrale.gestioncompte.model.Erreur;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ApiExceptionHandlerTest {

    private final ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler();

    @Test
    void handleEntityNotFound_shouldReturnErreurPayload() {
        ResponseEntity<Erreur> response = apiExceptionHandler.handleEntityNotFound(
                new EntityNotFoundException("Utilisateur introuvable")
        );

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("NOT_FOUND", response.getBody().getStatus());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getHttpStatus());
        assertEquals("Utilisateur introuvable", response.getBody().getMessage());
    }

    @Test
    void handleIllegalArgument_shouldReturnErreurPayload() {
        ResponseEntity<Erreur> response = apiExceptionHandler.handleIllegalArgument(
                new IllegalArgumentException("Rôle invalide")
        );

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("BAD_REQUEST", response.getBody().getStatus());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getHttpStatus());
        assertEquals("Rôle invalide", response.getBody().getMessage());
    }

    @Test
    void handleGenericException_shouldReturnErreurPayload() {
        ResponseEntity<Erreur> response = apiExceptionHandler.handleGenericException(
                new RuntimeException("Erreur inattendue")
        );

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("INTERNAL_SERVER_ERROR", response.getBody().getStatus());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().getHttpStatus());
        assertEquals("Erreur inattendue", response.getBody().getMessage());
    }
}
