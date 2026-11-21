package com.company.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Table(name = "products")
@EqualsAndHashCode(callSuper = true)
public class ProductEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "quantity")
    BigInteger quantity;
    @Column(name = "price")
    BigDecimal price;
    @Column(name = "description")
    String description;
}
