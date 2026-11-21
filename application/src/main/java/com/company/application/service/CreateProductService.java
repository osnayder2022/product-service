package com.company.application.service;

import com.company.domain.exception.ExistenceProductException;
import com.company.application.port.in.CreateProductUseCase;
import com.company.domain.port.out.ProductRepositoryPort;
import com.company.domain.entity.Product;

public class CreateProductService implements CreateProductUseCase {

    private final ProductRepositoryPort productRepository;

    public CreateProductService(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product execute(Product product) {
        /* Valida que tenga tengas todos los campos obligatorios */
        product.hasFieldMandatory();

        /* Regla de negocio que valida que no existan mas de un producto con el mismo nombre */
        this.validateExistenceProduct(product);

        /* Se guarda el nuevo producto */
        return productRepository.save(product);
    }

    private void validateExistenceProduct(Product product) {
        if (productRepository.existsByName(product.getName().getValue())) {
            throw new ExistenceProductException("Product with name " + product.getName().getValue() + " already exists.");
        }
    }
}
