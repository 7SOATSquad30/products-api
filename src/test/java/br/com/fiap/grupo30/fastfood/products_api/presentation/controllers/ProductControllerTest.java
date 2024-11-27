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
import com.fasterxml.jackson.databind.ObjectMapper;
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
            // Arrange
            Long productId = 1L;
            ProductDTO productDTO = ProductHelper.createDefaultProductDTOWithId(productId);
            when(getProductUseCase.execute(any(ProductGateway.class), eq(productId)))
                    .thenReturn(productDTO);

            // Act & Assert
            mockMvc.perform(
                            get("/products/{id}", productId)
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.productId").value(productId))
                    .andExpect(jsonPath("$.name").value(productDTO.getName()))
                    .andExpect(jsonPath("$.description").value(productDTO.getDescription()))
                    .andExpect(jsonPath("$.price").value(productDTO.getPrice()))
                    .andExpect(jsonPath("$.imgUrl").value(productDTO.getImgUrl()))
                    .andExpect(jsonPath("$.category").value(productDTO.getCategory()));

            // Verify
            verify(getProductUseCase, times(1)).execute(any(ProductGateway.class), eq(1L));
        }
    }

    @Nested
    class PostProduct {
        @Test
        void shouldCreateProductAndReturn201() throws Exception {
            // Arrange
            ProductDTO productDTO = ProductHelper.createDefaultProductDTOWithId(1L);
            when(createProductUseCase.execute(
                            any(ProductGateway.class),
                            any(CategoryGateway.class),
                            any(ProductDTO.class)))
                    .thenReturn(productDTO);

            // Act & Assert
            String jsonContent = new ObjectMapper().writeValueAsString(productDTO);
            mockMvc.perform(
                            post("/products")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(jsonContent))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name").value("Burger"));

            // Verify
            verify(createProductUseCase, times(1))
                    .execute(
                            any(ProductGateway.class),
                            any(CategoryGateway.class),
                            any(ProductDTO.class));
        }

        @Test
        void shouldInvokeCreateProductUseCase() throws Exception {
            // Arrange
            ProductDTO productDTO = ProductHelper.createDefaultProductDTOWithId(1L);

            // Act & Assert
            String jsonContent = new ObjectMapper().writeValueAsString(productDTO);
            mockMvc.perform(
                            post("/products")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(jsonContent))
                    .andReturn();

            // Verify
            verify(createProductUseCase, times(1))
                    .execute(
                            any(ProductGateway.class),
                            any(CategoryGateway.class),
                            any(ProductDTO.class));
        }
    }

    @Nested
    class PutProduct {
        @Test
        void shouldUpdateProductAndReturn200() throws Exception {
            // Arrange
            Long productId = 1L;
            ProductDTO updatedProductDTO =
                    ProductHelper.createUpdatedProductDTO(
                            productId,
                            "Updated Burger",
                            "Delicious updated burger",
                            15.99,
                            "http://example.com/updated-burger.png",
                            "Snacks");

            when(updateProductUseCase.execute(
                            any(ProductGateway.class),
                            any(CategoryGateway.class),
                            any(ProductDTO.class)))
                    .thenReturn(updatedProductDTO);

            // Act & Assert
            String jsonContent = new ObjectMapper().writeValueAsString(updatedProductDTO);
            mockMvc.perform(
                            put("/products/{id}", productId)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(jsonContent))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.productId").value(productId))
                    .andExpect(jsonPath("$.name").value("Updated Burger"))
                    .andExpect(jsonPath("$.price").value(15.99))
                    .andExpect(jsonPath("$.description").value("Delicious updated burger"))
                    .andExpect(jsonPath("$.imgUrl").value("http://example.com/updated-burger.png"));

            // Verify
            verify(updateProductUseCase, times(1))
                    .execute(
                            any(ProductGateway.class),
                            any(CategoryGateway.class),
                            any(ProductDTO.class));
        }
    }

    @Nested
    class DeleteProduct {
        @Test
        void shouldDeleteProductAndReturn204() throws Exception {
            // Act & Assert
            Long productId = 1L;
            mockMvc.perform(delete("/products/{1}", productId)).andExpect(status().isNoContent());

            // Verify
            verify(deleteProductUseCase, times(1)).execute(any(ProductGateway.class), eq(1L));
        }

        @Test
        void shouldInvokeDeleteProductUseCase() throws Exception {
            fail("Test not implemented");
        }
    }
}
