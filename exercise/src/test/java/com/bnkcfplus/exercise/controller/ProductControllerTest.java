package com.bnkcfplus.exercise.controller;

import com.bnkcfplus.exercise.dto.ProductDto;
import com.bnkcfplus.exercise.exceptions.ValidationException;
import com.bnkcfplus.exercise.model.Product;
import com.bnkcfplus.exercise.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductService service;

    @Test
    void retrieveProducts() {
        Mockito.when(service.retrieveAllProducts()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.retrieveProducts().getStatusCode());
    }

    @Test
    void getProduct() {
        Mockito.when(service.findProduct(32)).thenReturn(Optional.of(ResponseEntity.ok(new ProductDto())));
        Assertions.assertEquals(HttpStatus.OK, controller.getProduct(32).getStatusCode());
        Mockito.when(service.findProduct(32)).thenReturn(Optional.empty());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getProduct(32).getStatusCode());
    }

    @Test
    void postProduct() throws ValidationException {
        Assertions.assertThrows(ValidationException.class, () -> {
            controller.postProduct(new Product());
        });
        Product product = new Product(123L, "asb", BigDecimal.ONE);
        Mockito.when(service.saveProduct(product)).thenReturn(Optional.of(new ProductDto()));
        Assertions.assertEquals(HttpStatus.CREATED, controller.postProduct(product).getStatusCode());
    }

    @Test
    void putProduct() throws ValidationException {
        Assertions.assertThrows(ValidationException.class, () -> {
            controller.putProduct(new Product());
        });
        Product product = new Product(123L, "asb", BigDecimal.ONE);
        Mockito.when(service.update(product)).thenReturn(Optional.of(new ProductDto()));
        Assertions.assertEquals(HttpStatus.OK, controller.putProduct(product).getStatusCode());
    }

    @Test
    void handleTest() {
        Assertions.assertEquals(HttpStatus.SERVICE_UNAVAILABLE, controller.handle(new SQLException("SQL EXC")).getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.handle(new ValidationException("validation error")).getStatusCode());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.handle(new ArithmeticException("validation error")).getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.handle(new DataIntegrityViolationException("integrity error")).getStatusCode());
    }
}