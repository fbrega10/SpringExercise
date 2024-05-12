package com.bnkcfplus.exercise.validator;

import com.bnkcfplus.exercise.exceptions.ValidationException;
import com.bnkcfplus.exercise.model.Product;

import java.util.Optional;

public class ProductValidator {

    public static Void validatePostProduct(Product product) throws ValidationException {
        Optional.ofNullable(product)
                .filter(prod -> prod.getName() != null && prod.getPrice() != null)
                .orElseThrow(ValidationException::new);
        return null;
    }

    public static Void validatePutProduct(Product product) throws ValidationException {
        Optional.ofNullable(product)
                .filter(prod -> prod.getName() != null && prod.getPrice() != null && prod.getId() != null)
                .orElseThrow(ValidationException::new);
        return null;
    }
}
