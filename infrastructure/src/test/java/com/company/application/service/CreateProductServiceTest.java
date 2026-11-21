package com.company.application.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import com.company.domain.port.out.ProductRepositoryPort;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import com.company.domain.entity.ProductName;
import org.junit.jupiter.api.DisplayName;
import com.company.domain.entity.Product;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.mockito.Mock;


@ExtendWith(MockitoExtension.class)
public class CreateProductServiceTest {

    @Mock
    private ProductRepositoryPort productRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @Test
    @DisplayName("Test creating a new product")
    public void createNewProduct() {
        /* Arrange */
        Product productExpected = new Product(1L, new ProductName("Manzana"), new BigInteger("10"), new BigDecimal("10.5"), "Description of Product A");
        Product productMock = new Product(1L, new ProductName("Manzana"), new BigInteger("10"), new BigDecimal("10.5"), "Description of Product A");
        when(productRepository.save(any(Product.class))).thenReturn(productMock);

        /* Act */
        Product productActual = this.createProductService.execute(productExpected);

        /* Assert */
        assertEquals(productExpected.getId(), productActual.getId(), "The product ID should match the expected value.");
        assertEquals(productExpected.getName().getValue(), productActual.getName().getValue(), "The product name should match the expected value.");
        assertEquals(productExpected.getQuantity(), productActual.getQuantity(), "The product quantity should match the expected value.");
        assertEquals(productExpected.getPrice(), productActual.getPrice(), "The product price should match the expected value.");
        assertEquals(productExpected.getDescription(), productActual.getDescription(), "The product description should match the expected value.");
    }


}
