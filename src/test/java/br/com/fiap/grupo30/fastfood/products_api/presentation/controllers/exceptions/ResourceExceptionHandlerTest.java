package br.com.fiap.grupo30.fastfood.products_api.presentation.controllers.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.exceptions.DatabaseException;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.exceptions.ResourceNotFoundException;
import java.util.Objects;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

class ResourceExceptionHandlerTest {

    private static final String PATH_VARIABLE = "/products";
    private static final String PATH_VARIABLE_ID = "/products/{id}";

    @Nested
    class ResourceNotFoundExceptionHandler {
        @Test
        void shouldHandleResourceNotFoundExceptionAndReturn404() {
            // Arrange
            ResourceNotFoundException exception =
                    new ResourceNotFoundException("Resource not found");
            MockHttpServletRequest request = new MockHttpServletRequest();
            request.setRequestURI(PATH_VARIABLE_ID);

            ResourceExceptionHandler handler = new ResourceExceptionHandler();

            // Act
            ResponseEntity<StandardError> response = handler.entityNotFound(exception, request);

            // Assert
            assertEquals(
                    HttpStatus.NOT_FOUND,
                    response.getStatusCode(),
                    "Expected HTTP status NOT_FOUND (404)");
        }

        @Test
        void shouldReturnCorrectErrorMessageForResourceNotFoundException() {
            // Arrange
            ResourceNotFoundException exception =
                    new ResourceNotFoundException("Resource not found");
            MockHttpServletRequest request = new MockHttpServletRequest();
            request.setRequestURI(PATH_VARIABLE_ID);

            ResourceExceptionHandler handler = new ResourceExceptionHandler();

            // Act
            ResponseEntity<StandardError> response = handler.entityNotFound(exception, request);

            // Assert
            assertEquals(
                    "Resource not found",
                    Objects.requireNonNull(response.getBody()).getError(),
                    "Error message should match exception message");
        }
    }

    @Nested
    class DatabaseExceptionHandler {
        @Test
        void shouldHandleDatabaseExceptionAndReturn400() {
            // Arrange
            DatabaseException exception = new DatabaseException("Database exception");
            MockHttpServletRequest request = new MockHttpServletRequest();
            request.setRequestURI(PATH_VARIABLE);

            ResourceExceptionHandler handler = new ResourceExceptionHandler();

            // Act
            ResponseEntity<StandardError> response = handler.database(exception, request);

            // Assert
            assertEquals(
                    HttpStatus.BAD_REQUEST,
                    response.getStatusCode(),
                    "Expected HTTP status BAD_REQUEST (400)");
        }

        @Test
        void shouldReturnCorrectErrorMessageForDatabaseException() {
            // Arrange
            DatabaseException exception = new DatabaseException("Database exception");
            MockHttpServletRequest request = new MockHttpServletRequest();
            request.setRequestURI(PATH_VARIABLE);

            ResourceExceptionHandler handler = new ResourceExceptionHandler();

            // Act
            ResponseEntity<StandardError> response = handler.database(exception, request);

            // Assert
            assertEquals(
                    "Database exception",
                    Objects.requireNonNull(response.getBody()).getError(),
                    "Error message should match exception message");
        }
    }

    @Nested
    class MethodArgumentNotValidExceptionHandler {
        @Test
        void shouldHandleValidationExceptionAndReturn422() {
            fail("Test not implemented");
        }

        @Test
        void shouldReturnValidationErrorDetails_exceptionMessage() {
            fail("Test not implemented");
        }

        @Test
        void shouldReturnValidationErrorDetails_singleError() {
            fail("Test not implemented");
        }

        @Test
        void shouldReturnValidationErrorDetails_errorFieldName() {
            fail("Test not implemented");
        }

        @Test
        void shouldReturnValidationErrorDetails_errorMessage() {
            fail("Test not implemented");
        }
    }
}
