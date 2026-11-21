package com.company.infrastructure.adapter.in.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.company.application.port.in.CreateProductUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import com.company.application.dto.CreateProductDto;
import com.company.domain.entity.Product;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final ModelMapper mapper;

    public ProductController(CreateProductUseCase createProductUseCase, ModelMapper mapper) {
        this.createProductUseCase = createProductUseCase;
        this.mapper = mapper;
    }

    @PostMapping("")
    public CreateProductDto createProduct(@RequestBody CreateProductDto createProductoDto) {
        Product product = mapper.map(createProductoDto, Product.class);
        Product productSaved =  createProductUseCase.execute(product);

        return mapper.map(productSaved, CreateProductDto.class);
    }
}
