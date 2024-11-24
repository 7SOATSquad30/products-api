package br.com.fiap.grupo30.fastfood.products_api.domain.entities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.CategoryDTO;
import org.junit.jupiter.api.Test;

class CategoryTest {

    private static final String CATEGORY_NAME = "Drinks";

    @Test
    void shouldCreateCategoryWithNullId() {
        // Act
        Category category = Category.create(CATEGORY_NAME);

        // Assert
        assertThat(category.getId()).isNull();
    }

    @Test
    void shouldCreateCategoryWithCorrectName() {
        // Act
        Category category = Category.create(CATEGORY_NAME);

        // Assert
        assertThat(category.getName()).isEqualTo(CATEGORY_NAME);
    }

    @Test
    void shouldConvertToDTOWithCorrectName() {
        // Arrange
        Category category = new Category(1L, CATEGORY_NAME);

        // Act
        CategoryDTO dto = category.toDTO();

        // Assert
        assertThat(dto.getName()).isEqualTo(category.getName());
    }

    @Test
    void shouldConvertToPersistenceEntityWithCorrectId() {
        fail("test not implemented");
    }

    @Test
    void shouldConvertToPersistenceEntityWithCorrectName() {
        fail("test not implemented");
    }

    @Test
    void shouldBeEqualIfNamesAreSame() {
        fail("test not implemented");
    }

    @Test
    void shouldHaveSameHashCodeIfNamesAreSame() {
        fail("test not implemented");
    }

    @Test
    void shouldNotBeEqualIfNamesAreDifferent() {
        fail("test not implemented");
    }
}
