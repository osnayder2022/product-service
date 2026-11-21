package com.company.infrastructure.adapter.out.persistence;

import com.company.infrastructure.adapter.out.persistence.repositories.ProductDbRepository;
import com.company.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.company.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Component;
import com.company.domain.entity.Product;
import org.modelmapper.ModelMapper;

@Component
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final ProductDbRepository productRepository;
    private final ModelMapper mapper;

    public ProductPersistenceAdapter(ProductDbRepository productRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = mapper.map(product, ProductEntity.class);
        productEntity.setId(null); // Ensure the ID is null for a new entity

        ProductEntity savedEntity = productRepository.save(productEntity);

        return mapper.map(savedEntity, Product.class);

    }

    @Override
    public Product update(Product product) {
        ProductEntity productEntity = mapper.map(product, ProductEntity.class);
        ProductEntity updatedEntity = productRepository.save(productEntity);
        return mapper.map(updatedEntity, Product.class);
    }

    @Override
    public Product findById(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        return mapper.map(productEntity, Product.class);
    }

    @Override
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
