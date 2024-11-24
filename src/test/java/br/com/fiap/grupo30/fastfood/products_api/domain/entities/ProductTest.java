package br.com.fiap.grupo30.fastfood.products_api.domain.entities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

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
        fail("Test not implemented");
    }

    @Test
    void shouldCreateProductWithDescription() {
        fail("Test not implemented");
    }

    @Test
    void shouldCreateProductWithPrice() {
        fail("Test not implemented");
    }

    @Test
    void shouldCreateProductWithImgUrl() {
        fail("Test not implemented");
    }

    @Test
    void shouldCreateProductWithCategory() {
        fail("Test not implemented");
    }

    @Test
    void shouldConvertToDTOWithName() {
        fail("Test not implemented");
    }

    @Test
    void shouldConvertToPersistenceWithId() {
        fail("Test not implemented");
    }

    @Test
    void shouldCompareEqualityWhenIdsMatch() {
        fail("Test not implemented");
    }

    @Test
    void shouldNotCompareEqualityWhenIdsDiffer() {
        fail("Test not implemented");
    }
}
