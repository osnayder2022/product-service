package com.company.application.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CreateProductDto {
    private String name;
    private BigInteger quantity;
    private BigDecimal price;
    private String description;

    public CreateProductDto() {}

    public CreateProductDto(String name, BigInteger quantity, BigDecimal price, String description) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public BigInteger getQuantity() { return quantity; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
