package com.company.infrastructure.config;

import com.company.application.service.CreateProductService;
import org.springframework.context.annotation.Configuration;
import com.company.domain.port.out.ProductRepositoryPort;
import org.springframework.context.annotation.Bean;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateProductService createProductService(ProductRepositoryPort productRepository) {
        return new CreateProductService(productRepository);
    }
}
