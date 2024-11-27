package br.com.fiap.grupo30.fastfood.products_api;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductsApiApplicationTest {

    @Test
    void applicationContextShouldLoad() {
        // Act & Assert
        assertNotNull(ProductsApiApplication.class, "Application class should not be null");
    }
}
