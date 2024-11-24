package br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Category;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.entities.CategoryEntity;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.repositories.JpaCategoryRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CategoryGatewayTest {

    @Mock private JpaCategoryRepository jpaCategoryRepository;

    @InjectMocks private CategoryGateway categoryGateway;

    private static final Long CATEGORY_ID = 1L;
    private static final String CATEGORY_NAME = "Drinks";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Testes para findAll
    @Test
    void findAllShouldReturnListWithCorrectSize() {
        // Arrange
        CategoryEntity entity = new CategoryEntity(CATEGORY_ID, CATEGORY_NAME);
        when(jpaCategoryRepository.findAll()).thenReturn(List.of(entity));

        // Act
        List<Category> categories = categoryGateway.findAll();

        // Assert
        assertThat(categories).hasSize(1);
    }

    @Test
    void findAllShouldVerifyRepositoryCall() {
        // Arrange
        CategoryEntity entity = new CategoryEntity(CATEGORY_ID, CATEGORY_NAME);
        when(jpaCategoryRepository.findAll()).thenReturn(List.of(entity));

        // Act
        categoryGateway.findAll();

        // Verify
        verify(jpaCategoryRepository, times(1)).findAll();
    }

    @Test
    void findAllShouldReturnCategoryWithCorrectId() {
        fail("test not implemented");
    }

    @Test
    void findAllShouldReturnCategoryWithCorrectName() {
        fail("test not implemented");
    }

    // Testes para findOne
    @Test
    void findOneShouldReturnCategoryWithCorrectId() {
        fail("test not implemented");
    }

    @Test
    void findOneShouldReturnCategoryWithCorrectName() {
        fail("test not implemented");
    }

    @Test
    void findOneShouldVerifyRepositoryCall() {
        fail("test not implemented");
    }

    @Test
    void findOneShouldThrowExceptionIfNotFound() {
        fail("test not implemented");
    }

    @Test
    void findOneShouldVerifyRepositoryCallOnException() {
        fail("test not implemented");
    }
}
