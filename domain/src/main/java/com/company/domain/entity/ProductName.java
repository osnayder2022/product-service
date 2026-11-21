package com.company.domain.entity;

import com.company.domain.exception.NameProductException;
import com.company.domain.exception.FieldEmptyException;
import java.util.regex.Pattern;

// Ejemplo de Value Object u Objeto de Valor para el nombre del producto
public class ProductName {
    private static final Pattern ONLY_LETTERS_WITHOUT_SPACES = Pattern.compile("^[a-zA-Z]+$");
    private String value;

    public ProductName(String value) {
        this.validateName(value);
        this.value = value;
    }

    public void setValue(String value) {
        this.validateName(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new FieldEmptyException("Product name cannot be empty");
        }

        if (!ONLY_LETTERS_WITHOUT_SPACES.matcher(name).matches()){
            throw new NameProductException("Name must contain only letters and no spaces.");
        }
    }
}
