package br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Product;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.ProductGateway;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.ProductDTO;
import br.com.fiap.grupo30.fastfood.products_api.utils.ProductHelper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ListProductsByCategoryUseCaseTest {

    private ListProductsByCategoryUseCase listProductsByCategoryUseCase;

    @Mock private ProductGateway productGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        listProductsByCategoryUseCase = new ListProductsByCategoryUseCase();
    }

    @Test
    void shouldReturnListOfProductAsDTOs() {
        // Arrange
        Long categoryId = 1L;
        Product product1 = ProductHelper.createProductWithIdAndCategory(1L, categoryId);
        Product product2 = ProductHelper.createProductWithIdAndCategory(2L, categoryId);
        List<Product> products = List.of(product1, product2);

        when(productGateway.findProductsByCategoryId(categoryId)).thenReturn(products);

        // Act
        List<ProductDTO> result = listProductsByCategoryUseCase.execute(productGateway, categoryId);

        // Assert
        assertThat(result).hasSize(2);
    }

    @Test
    void shouldReturnFirstProductWithCorrectName() {
        fail("Test not implemented");
    }

    @Test
    void shouldReturnSecondProductWithCorrectName() {
        fail("Test not implemented");
    }

    @Test
    void shouldReturnEmptyListWhenNoProductsExist() {
        fail("Test not implemented");
    }

    @Test
    void shouldCallFindProductsByCategoryId() {
        fail("Test not implemented");
    }
}
