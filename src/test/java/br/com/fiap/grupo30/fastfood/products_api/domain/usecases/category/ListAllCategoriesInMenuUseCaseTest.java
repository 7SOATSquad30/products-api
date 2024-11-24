package br.com.fiap.grupo30.fastfood.products_api.domain.usecases.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Category;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.CategoryGateway;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.CategoryDTO;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ListAllCategoriesInMenuUseCaseTest {

    private static final String FIRST_CATEGORY_NAME = "Drinks";
    private static final String SECOND_CATEGORY_NAME = "Snacks";
    private ListAllCategoriesInMenuUseCase useCase;

    @Mock private CategoryGateway categoryGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new ListAllCategoriesInMenuUseCase();
    }

    @Test
    void shouldReturnAllCategoriesAsDTOs() {
        // Arrange
        List<Category> mockCategories =
                List.of(
                        new Category(1L, FIRST_CATEGORY_NAME),
                        new Category(2L, SECOND_CATEGORY_NAME));
        when(categoryGateway.findAll()).thenReturn(mockCategories);

        // Act
        List<CategoryDTO> result = useCase.execute(categoryGateway);

        // Assert
        assertThat(result).hasSize(2);
    }

    @Test
    void shouldReturnFirstCategoryWithCorrectName() {
        fail("test not implemented");
    }

    @Test
    void shouldReturnSecondCategoryWithCorrectName() {
        fail("test not implemented");
    }

    @Test
    void shouldReturnEmptyListWhenNoCategoriesExist() {
        fail("test not implemented");
    }

    @Test
    void shouldCallFindAllOnCategoryGateway() {
        fail("test not implemented");
    }
}