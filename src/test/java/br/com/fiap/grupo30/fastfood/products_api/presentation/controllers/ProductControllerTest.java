package br.com.fiap.grupo30.fastfood.products_api.presentation.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product.*;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.CategoryGateway;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.ProductGateway;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.ProductDTO;
import br.com.fiap.grupo30.fastfood.products_api.utils.ProductHelper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock private CreateProductUseCase createProductUseCase;
    @Mock private DeleteProductUseCase deleteProductUseCase;
    @Mock private GetProductUseCase getProductUseCase;
    @Mock private ListProductsByCategoryUseCase listProductsByCategoryUseCase;
    @Mock private UpdateProductUseCase updateProductUseCase;
    @Mock private ProductGateway productGateway;
    @Mock private CategoryGateway categoryGateway;

    @InjectMocks private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Nested
    class GetProduct {
        @Test
        void shouldReturnProductsByCategoryId() throws Exception {
            // Arrange
            ProductDTO productDTO = ProductHelper.createDefaultProductDTOWithId(1L);
            when(listProductsByCategoryUseCase.execute(any(ProductGateway.class), eq(1L)))
                    .thenReturn(List.of(productDTO));

            // Act & Assert
            mockMvc.perform(get("/products?categoryId=1").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()").value(1));

            // Verify
            verify(listProductsByCategoryUseCase, times(1))
                    .execute(any(ProductGateway.class), eq(1L));
        }

        @Test
        void shouldInvokeListProductsByCategoryIdUseCase() throws Exception {
            // Act & Assert
            mockMvc.perform(get("/products?categoryId=1")).andReturn();

            // Verify
            verify(listProductsByCategoryUseCase, times(1))
                    .execute(any(ProductGateway.class), eq(1L));
        }

        @Test
        void shouldReturnProductById() throws Exception {
            fail("Test not implemented");
        }
    }

    @Nested
    class PostProduct {
        @Test
        void shouldCreateProductAndReturn201() throws Exception {
            fail("Test not implemented");
        }

        @Test
        void shouldInvokeCreateProductUseCase() throws Exception {
            fail("Test not implemented");
        }
    }

    @Nested
    class PutProduct {
        @Test
        void shouldUpdateProductAndReturn200() throws Exception {
            fail("Test not implemented");
        }
    }

    @Nested
    class DeleteProduct {
        @Test
        void shouldDeleteProductAndReturn204() throws Exception {
            fail("Test not implemented");
        }

        @Test
        void shouldInvokeDeleteProductUseCase() throws Exception {
            fail("Test not implemented");
        }
    }
}
