package br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Category;
import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Product;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.CategoryGateway;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.ProductGateway;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.ProductDTO;

public class CreateProductUseCase {

    public ProductDTO execute(
            ProductGateway productGateway, CategoryGateway categoryGateway, ProductDTO dto) {
        Category categoryEntity = categoryGateway.findOne(dto.getCategory());
        Product product =
                Product.create(
                        dto.getName(),
                        dto.getDescription(),
                        dto.getPrice(),
                        dto.getImgUrl(),
                        categoryEntity);
        return productGateway.save(product).toDTO();
    }
}
