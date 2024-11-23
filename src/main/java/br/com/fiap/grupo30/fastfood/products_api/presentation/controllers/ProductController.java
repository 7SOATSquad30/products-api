package br.com.fiap.grupo30.fastfood.products_api.presentation.controllers;

import br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product.GetProductUseCase;
import br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product.ListProductsByCategoryUseCase;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.ProductGateway;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.repositories.JpaProductRepository;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products")
@Tag(name = "Products Controller", description = "RESTful API for managing products.")
public class ProductController {

    private static final String PATH_VARIABLE_ID = "/{id}";

    private final GetProductUseCase getProductUseCase;
    private final ListProductsByCategoryUseCase listProductsByCategoryUseCase;
    private final JpaProductRepository jpaProductRepository;

    @Autowired
    public ProductController(
            GetProductUseCase getProductUseCase,
            ListProductsByCategoryUseCase listProductsByCategoryUseCase,
            JpaProductRepository jpaProductRepository) {

        this.getProductUseCase = getProductUseCase;
        this.listProductsByCategoryUseCase = listProductsByCategoryUseCase;
        this.jpaProductRepository = jpaProductRepository;
    }

    @GetMapping
    @Operation(
            summary = "Get all products",
            description =
                    "Retrieve a list of all registered products or by categoryId "
                            + "via RequestParam. i.e., ?categoryId=1")
    public ResponseEntity<List<ProductDTO>> findProductsByCategoryId(
            @RequestParam(value = "categoryId", defaultValue = "0") Long categoryId) {
        ProductGateway productGateway = new ProductGateway(jpaProductRepository);
        List<ProductDTO> products =
                this.listProductsByCategoryUseCase.execute(productGateway, categoryId);
        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = PATH_VARIABLE_ID)
    @Operation(
            summary = "Get a product by ID",
            description = "Retrieve a specific product based on its ID")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Long id) {
        ProductGateway productGateway = new ProductGateway(jpaProductRepository);
        ProductDTO dto = this.getProductUseCase.execute(productGateway, id);
        return ResponseEntity.ok().body(dto);
    }
}
