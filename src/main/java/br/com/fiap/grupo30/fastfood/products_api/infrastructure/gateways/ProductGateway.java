package br.com.fiap.grupo30.fastfood.products_api.infrastructure.gateways;

import br.com.fiap.grupo30.fastfood.products_api.domain.entities.Product;
import br.com.fiap.grupo30.fastfood.products_api.domain.repositories.ProductRepository;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.entities.ProductEntity;
import br.com.fiap.grupo30.fastfood.products_api.infrastructure.persistence.repositories.JpaProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductGateway implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Autowired
    public ProductGateway(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductsByCategoryId(Long categoryId) {
        Long category = categoryId == 0 ? null : categoryId;
        return jpaProductRepository.findProductsByCategoryId(category).stream()
                .map(ProductEntity::toDomainEntity)
                .toList();
    }
}
