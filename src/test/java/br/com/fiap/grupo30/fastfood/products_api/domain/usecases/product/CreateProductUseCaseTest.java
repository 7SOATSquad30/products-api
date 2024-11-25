package br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Category;
import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Product;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.CategoryGateway;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.ProductGateway;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.ProductDTO;
import br.com.fiap.grupo30.fastfood.products_api.utils.ProductHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CreateProductUseCaseTest {

    @Mock private ProductGateway productGateway;

    @Mock private CategoryGateway categoryGateway;

    @InjectMocks private CreateProductUseCase createProductUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnProductDTOWithCorrectName() {
        // Arrange
        Category category = ProductHelper.createDefaultCategory();
        Product product = ProductHelper.createDefaultProduct();
        ProductDTO productDTO = ProductHelper.createDefaultProductDTO();

        when(categoryGateway.findOne("Snacks")).thenReturn(category);
        when(productGateway.save(any(Product.class))).thenReturn(product);

        // Act
        ProductDTO result =
                createProductUseCase.execute(productGateway, categoryGateway, productDTO);

        // Assert
        assertThat(result.getName()).isEqualTo(productDTO.getName());
    }

    @Test
    void shouldCallCategoryGatewayFindOne() {
        // Arrange
        Category category = ProductHelper.createDefaultCategory();
        Product product = ProductHelper.createDefaultProduct();
        ProductDTO productDTO = ProductHelper.createDefaultProductDTO();
        when(categoryGateway.findOne("Snacks")).thenReturn(category);
        when(productGateway.save(any(Product.class))).thenReturn(product);

        // Act
        createProductUseCase.execute(productGateway, categoryGateway, productDTO);

        // Verify
        verify(categoryGateway).findOne("Snacks");
    }

    @Test
    void shouldCallProductGatewaySave() {
        fail("Test not implemented");
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound() {
        fail("Test not implemented");
    }

    @Test
    void shouldNotCallProductGatewayWhenCategoryNotFound() {
        fail("Test not implemented");
    }
}
