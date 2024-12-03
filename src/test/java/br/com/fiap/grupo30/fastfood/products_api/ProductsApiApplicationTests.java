package br.com.fiap.grupo30.fastfood.products_api;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductsApiApplicationTests {

    @Test
    void applicationContextShouldLoad() {
        // Act & Assert
        assertNotNull(ProductsApiApplication.class, "Application class should not be null");
    }

    @Test
    void mainMethodShouldStartApplication() {
        // Arrange
        String[] args = {};

        // Act & Assert
        assertDoesNotThrow(
                () -> ProductsApiApplication.main(args),
                "Main method should execute without throwing exceptions");
    }
}
