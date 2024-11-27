package br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Product;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.entities.ProductEntity;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.repositories.JpaProductRepository;
import br.com.fiap.grupo30.fastfood.products_api.utils.ProductHelper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ProductGatewayTest {

    private JpaProductRepository jpaProductRepository;
    private ProductGateway productGateway;

    @BeforeEach
    void setUp() {
        jpaProductRepository = mock(JpaProductRepository.class);
        productGateway = new ProductGateway(jpaProductRepository);
    }

    @Nested
    class FindProductsByCategoryId {
        @Test
        void shouldReturnProductsByCategoryIdWithCorrectSize() {
            // Arrange
            ProductEntity entity = ProductHelper.createDefaultProduct().toPersistence();
            when(jpaProductRepository.findProductsByCategoryId(1L)).thenReturn(List.of(entity));

            // Act
            List<Product> result = productGateway.findProductsByCategoryId(1L);

            // Assert
            assertThat(result).hasSize(1);
        }

        @Test
        void shouldReturnProductsByCategoryIdWithCorrectName() {
            fail("Test not implemented");
        }

        @Test
        void shouldReturnEmptyListWhenNoProductsForCategoryId() {
            fail("Test not implemented");
        }
    }

    @Nested
    class FindProductById {
        @Test
        void shouldReturnProductById() {
            fail("Test not implemented");
        }

        @Test
        void shouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
            fail("Test not implemented");
        }
    }

    @Nested
    class SaveProduct {
        @Test
        void shouldSaveProduct() {
            fail("Test not implemented");
        }
    }

    @Nested
    class DeleteProduct {
        @Test
        void shouldDeleteProductById() {
            fail("Test not implemented");
        }

        @Test
        void shouldThrowResourceNotFoundExceptionWhenDeletingNonExistingId() {
            fail("Test not implemented");
        }

        @Test
        void shouldThrowDatabaseExceptionOnIntegrityViolationWhenDeleting() {
            fail("Test not implemented");
        }
    }
}
