package br.com.fiap.grupo30.fastfood.products_api.domain.entities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.entities.ProductEntity;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.ProductDTO;
import br.com.fiap.grupo30.fastfood.products_api.utils.ProductHelper;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void shouldCreateProductWithNullId() {
        // Act
        Product product = ProductHelper.createProduct();

        // Assert
        assertThat(product.getId()).isNull();
    }

    @Test
    void shouldCreateProductWithName() {
        // Act
        Product product = ProductHelper.createProduct();

        // Assert
        assertThat(product.getName()).isEqualTo("Burger");
    }

    @Test
    void shouldCreateProductWithDescription() {
        // Act
        Product product = ProductHelper.createProduct();

        // Assert
        assertThat(product.getDescription()).isEqualTo("Delicious burger");
    }

    @Test
    void shouldCreateProductWithPrice() {
        // Act
        Product product = ProductHelper.createProduct();

        // Assert
        assertThat(product.getPrice()).isEqualTo(12.99);
    }

    @Test
    void shouldCreateProductWithImgUrl() {
        // Act
        Product product = ProductHelper.createProduct();

        // Assert
        assertThat(product.getImgUrl()).isEqualTo("http://example.com/burger.png");
    }

    @Test
    void shouldCreateProductWithCategory() {
        // Arrange
        Category category = ProductHelper.generateCategory();

        // Act
        Product product = ProductHelper.createProduct();

        // Assert
        assertThat(product.getCategory()).isEqualTo(category);
    }

    @Test
    void shouldConvertToDTOWithName() {
        // Arrange
        Product product = ProductHelper.createProduct();
        product.setId(1L);

        // Act
        ProductDTO productDTO = product.toDTO();

        // Assert
        assertThat(productDTO.getName()).isEqualTo("Burger");
    }

    @Test
    void shouldConvertToPersistenceWithId() {
        // Arrange
        Product product = ProductHelper.createProduct();
        product.setId(1L);

        // Act
        ProductEntity productEntity = product.toPersistence();

        // Assert
        assertThat(productEntity.getId()).isEqualTo(1L);
    }

    @Test
    void shouldCompareEqualityWhenIdsMatch() {
        // Arrange
        Product product1 = ProductHelper.createProduct();
        product1.setId(1L);
        Product product2 =
                new Product(
                        1L,
                        "Pizza",
                        "Delicious pizza",
                        15.99,
                        "http://example.com/pizza.png",
                        new Category(2L, "Italian Food"));

        // Act & Assert
        assertThat(product1).isEqualTo(product2);
    }

    @Test
    void shouldNotCompareEqualityWhenIdsDiffer() {
        fail("Test not implemented");
    }
}
