package com.bnkcfplus.exercise.validator;

import com.bnkcfplus.exercise.exceptions.ValidationException;
import com.bnkcfplus.exercise.model.Product;

import java.util.Optional;

public class ProductValidator {

    public static void validatePostProduct(Product product) throws ValidationException {
        Optional.ofNullable(product)
                .filter(prod -> prod.getName() != null && prod.getPrice() != null)
                .orElseThrow(ValidationException::new);
    }

    public static void validatePutProduct(Product product) throws ValidationException {
        Optional.ofNullable(product)
                .filter(prod -> prod.getName() != null
                        && prod.getPrice() != null
                        && prod.getId() != null)
                .orElseThrow(ValidationException::new);
    }
}
