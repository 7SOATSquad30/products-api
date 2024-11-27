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
        // Arrange
        CategoryEntity category = new CategoryEntity();

        // Act
        category.preUpdate();

        // Assert
        assertNotNull(category.getUpdatedAt(), "UpdatedAt should be set during preUpdate");
    }

    @Test
    void preRemoveShouldSetDeletedAt() {
        // Arrange
        CategoryEntity category = new CategoryEntity();

        // Act
        category.preRemove();

        // Assert
        assertNotNull(category.getDeletedAt(), "DeletedAt should be set during preRemove");
    }
}
