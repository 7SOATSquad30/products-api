package br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.entities;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ProductEntityTest {

    @Test
    void prePersistShouldSetCreatedAt() {
        // Arrange
        ProductEntity product = new ProductEntity();

        // Act
        product.prePersist();

        // Assert
        assertNotNull(product.getCreatedAt(), "CreatedAt should be set during prePersist");
    }

    @Test
    void preUpdateShouldSetUpdatedAt() {
        // Arrange
        ProductEntity product = new ProductEntity();

        // Act
        product.preUpdate();

        // Assert
        assertNotNull(product.getUpdatedAt(), "UpdatedAt should be set during preUpdate");
    }
}
