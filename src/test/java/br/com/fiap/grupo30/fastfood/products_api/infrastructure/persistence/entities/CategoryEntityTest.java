package br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CategoryEntityTest {

    @Test
    void prePersistShouldSetCreatedAt() {
        // Arrange
        CategoryEntity category = new CategoryEntity();

        // Act
        category.prePersist();

        // Assert
        assertNotNull(category.getCreatedAt(), "CreatedAt should be set during prePersist");
    }

    @Test
    void preUpdateShouldSetUpdatedAt() {
        fail("Test not implemented");
    }

    @Test
    void preRemoveShouldSetDeletedAt() {
        fail("Test not implemented");
    }
}
