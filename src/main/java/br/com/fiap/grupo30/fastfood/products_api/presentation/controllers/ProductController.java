package br.com.fiap.grupo30.fastfood.products_api.presentation.controllers;

import br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product.CreateProductUseCase;
import br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product.GetProductUseCase;
import br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product.ListProductsByCategoryUseCase;
import br.com.fiap.grupo30.fastfood.products_api.domain.usecases.product.UpdateProductUseCase;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.CategoryGateway;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways.ProductGateway;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.repositories.JpaCategoryRepository;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.repositories.JpaProductRepository;
import br.com.fiap.grupo30.fastfood.products_api.presentation.presenters.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/products")
@Tag(name = "Products Controller", description = "RESTful API for managing products.")
public class ProductController {

    private static final String PATH_VARIABLE_ID = "/{id}";

    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final ListProductsByCategoryUseCase listProductsByCategoryUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final JpaProductRepository jpaProductRepository;
    private final JpaCategoryRepository jpaCategoryRepository;

    @Autowired
    public ProductController(
            CreateProductUseCase createProductUseCase,
            GetProductUseCase getProductUseCase,
            ListProductsByCategoryUseCase listProductsByCategoryUseCase,
            UpdateProductUseCase updateProductUseCase,
            JpaProductRepository jpaProductRepository,
            JpaCategoryRepository jpaCategoryRepository) {

        this.createProductUseCase = createProductUseCase;
        this.getProductUseCase = getProductUseCase;
        this.listProductsByCategoryUseCase = listProductsByCategoryUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.jpaProductRepository = jpaProductRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;
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

    @PostMapping
    @Operation(
            summary = "Create a new product",
            description = "Create a new product and return the created product's data")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO dto) {
        ProductGateway productGateway = new ProductGateway(jpaProductRepository);
        CategoryGateway categoryGateway = new CategoryGateway(jpaCategoryRepository);
        ProductDTO dtoCreated =
                this.createProductUseCase.execute(
                        productGateway,
                        categoryGateway,
                        dto.getName(),
                        dto.getDescription(),
                        dto.getPrice(),
                        dto.getImgUrl(),
                        dto.getCategory());
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path(PATH_VARIABLE_ID)
                        .buildAndExpand(dto.getProductId())
                        .toUri();
        return ResponseEntity.created(uri).body(dtoCreated);
    }

    @PutMapping(value = PATH_VARIABLE_ID)
    @Operation(
            summary = "Update a product",
            description = "Update the data of an existing product based on its ID")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id, @RequestBody @Valid ProductDTO dto) {
        ProductGateway productGateway = new ProductGateway(jpaProductRepository);
        CategoryGateway categoryGateway = new CategoryGateway(jpaCategoryRepository);
        ProductDTO dtoUpdated =
                this.updateProductUseCase.execute(
                        productGateway,
                        categoryGateway,
                        id,
                        dto.getName(),
                        dto.getDescription(),
                        dto.getPrice(),
                        dto.getImgUrl(),
                        dto.getCategory());
        return ResponseEntity.ok().body(dtoUpdated);
    }
}
