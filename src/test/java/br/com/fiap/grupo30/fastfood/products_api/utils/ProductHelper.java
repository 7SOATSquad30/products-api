package br.com.fiap.grupo30.fastfood.products_api.utils;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Category;
import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Product;

public class ProductHelper {

    public static Product generateProduct() {
        return Product.create(
                "Burger",
                "Delicious burger",
                12.99,
                "http://example.com/burger.png",
                new Category(1L, "Snacks"));
    }
}
