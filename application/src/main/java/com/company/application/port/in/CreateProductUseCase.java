package com.company.application.port.in;

import com.company.domain.entity.Product;

public interface CreateProductUseCase {
    Product execute(Product product);
}
