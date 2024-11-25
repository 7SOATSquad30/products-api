package br.com.fiap.grupo30.fastfood.products_api.utils;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Category;
import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Product;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.ProductDTO;

public class ProductHelper {

    public static Category createDefaultCategory() {
        return new Category(1L, "Snacks");
    }

    public static Product createDefaultProduct() {
        return Product.create(
                "Burger",
                "Delicious burger",
                12.99,
                "http://example.com/burger.png",
                createDefaultCategory());
    }

    public static Product createProductWithId(Long id) {
        Product product = createDefaultProduct();
        product.setId(id);
        return product;
    }

    public static ProductDTO createDefaultProductDTO() {
        Product product = createDefaultProduct();
        return product.toDTO();
    }

    public static ProductDTO createDefaultProductDTOWithId() {
        Product product = createDefaultProduct();
        product.setId(1L);
        return product.toDTO();
    }

    public static ProductDTO createDefaultProductDTOWithNonExistentCategory() {
        Product product = createDefaultProduct();
        product.setCategory(new Category(null, "Unknown"));
        return product.toDTO();
    }
}
