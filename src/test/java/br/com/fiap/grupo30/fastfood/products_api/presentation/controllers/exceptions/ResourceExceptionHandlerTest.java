package br.com.fiap.grupo30.fastfood.products_api.presentation.controllers.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.exceptions.ResourceNotFoundException;
import java.util.Objects;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

class ResourceExceptionHandlerTest {

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
            fail("Test not implemented");
        }

        @Test
        void shouldReturnCorrectErrorMessageForDatabaseException() {
            fail("Test not implemented");
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
