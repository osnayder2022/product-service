package com.company.domain.entity;

import com.company.domain.exception.PriceInvalidException;
import com.company.domain.exception.FieldEmptyException;
import java.math.BigDecimal;
import java.math.BigInteger;

// Ejemplo de entidad de dominio
public class Product {
    Long id;
    ProductName name;
    BigInteger quantity;
    BigDecimal price;
    String description;

    public Product(){}

    public Product(Long id, ProductName name, BigInteger quantity, BigDecimal price, String description) {
        validateQuantity(quantity);
        validatePrice(price);
        validateDescription(description);

        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(ProductName name) {
        this.name = name;
    }

    public void setQuantity(BigInteger quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        validatePrice(price);
        this.price = price;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    public Long getId() {return id;}
    public ProductName getName() {return name;}
    public BigInteger getQuantity() {return quantity;}
    public BigDecimal getPrice() {return price;}
    public String getDescription() {return description;}

    public void hasFieldMandatory() {
        if(this.name == null || this.name.getValue() == null || this.name.getValue().isEmpty()
                && this.price == null
                && this.quantity == null){
            throw new FieldEmptyException("Fields name, price and quantity are mandatory");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new FieldEmptyException("Product description cannot be empty");
        }
    }

    private void validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PriceInvalidException("Product price must be greater than zero");
        }
    }

    private void validateQuantity(BigInteger quantity) {
        if (quantity == null || quantity.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }
    }
}
