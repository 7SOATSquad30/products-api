package br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Category;
import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Product;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.CategoryGateway;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.ProductGateway;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.ProductDTO;

public class CreateProductUseCase {

    public ProductDTO execute(
            ProductGateway productGateway,
            CategoryGateway categoryGateway,
            String name,
            String description,
            Double price,
            String imgUrl,
            String category) {
        Category categoryEntity = categoryGateway.findOne(category);
        Product product = Product.create(name, description, price, imgUrl, categoryEntity);
        return productGateway.save(product).toDTO();
    }
}
