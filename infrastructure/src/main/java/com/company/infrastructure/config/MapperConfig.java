package com.company.infrastructure.config;

import com.company.application.dto.CreateProductDto;
import com.company.domain.entity.Product;
import com.company.domain.entity.ProductName;
import com.company.infrastructure.adapter.out.persistence.entity.ProductEntity;
import org.modelmapper.Converter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

import java.math.BigInteger;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // --------------------------------------------------------------------
        // 1. Mapeo: Product (Dominio) -> ProductEntity (Persistencia)
        // --------------------------------------------------------------------
        // Converter para extraer el String del Value Object ProductName
        Converter<ProductName, String> productNameToString = ctx ->
                ctx.getSource() != null ? ctx.getSource().getValue() : null; // Asumiendo getValue() en ProductName

        modelMapper.typeMap(Product.class, ProductEntity.class)
                .addMappings(mapper -> {
                    mapper.using(productNameToString)
                            .map(Product::getName, ProductEntity::setName);
                });

        // --------------------------------------------------------------------
        // 2. Mapeo: ProductEntity (Persistencia) -> Product (Dominio)
        // --------------------------------------------------------------------
        // Dado que Product requiere validaciones y el campo quantity no existe en Entity,
        // la forma más limpia y robusta es usar un Converter personalizado.
        Converter<ProductEntity, Product> entityToProductConverter = ctx -> {
            ProductEntity entity = ctx.getSource();
            if (entity == null) return null;

            // Instanciación directa asegurando que quantity tenga un valor por defecto válido (ej. BigInteger.ZERO)
            return new Product(
                    entity.getId(),
                    entity.getName() != null ? new ProductName(entity.getName()) : null,
                    BigInteger.ZERO, // Valor por defecto ya que ProductEntity no tiene 'quantity'
                    entity.getPrice(),
                    entity.getDescription()
            );
        };

        modelMapper.createTypeMap(ProductEntity.class, Product.class)
                .setConverter(entityToProductConverter);


        // --------------------------------------------------------------------
        // 3. Mapeo: Product (Dominio) -> CreateProductDto (DTO)
        // --------------------------------------------------------------------
        // Converter para extraer el String del Value Object ProductName
        Converter<ProductName, String> productNameToString2 = ctx ->
                ctx.getSource() != null ? ctx.getSource().getValue() : null; // Asumiendo getValue() en ProductName

        modelMapper.typeMap(Product.class, CreateProductDto.class)
                .addMappings(mapper -> {
                    mapper.using(productNameToString2)
                            .map(Product::getName, CreateProductDto::setName);
                });

        // --------------------------------------------------------------------
        // 4. Mapeo: CreateProductDto (DTO) -> Product (Dominio)
        // --------------------------------------------------------------------
        // Dado que Product requiere validaciones y el campo quantity no existe en Entity,
        // la forma más limpia y robusta es usar un Converter personalizado.
        Converter<CreateProductDto, Product> entityToProductConverter2 = ctx -> {
            CreateProductDto entity = ctx.getSource();
            if (entity == null) return null;

            // Instanciación directa asegurando que quantity tenga un valor por defecto válido (ej. BigInteger.ZERO)
            return new Product(
                    null, // id será null al crear un nuevo producto
                    entity.getName() != null ? new ProductName(entity.getName()) : null,
                    entity.getQuantity() != null ? entity.getQuantity() : BigInteger.ZERO, // Valor por defecto si es null
                    entity.getPrice(),
                    entity.getDescription()
            );
        };

        modelMapper.createTypeMap(CreateProductDto.class, Product.class)
                .setConverter(entityToProductConverter2);

        return modelMapper;
    }
}

