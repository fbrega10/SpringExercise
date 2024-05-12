package com.bnkcfplus.exercise.controller;


import com.bnkcfplus.exercise.dto.ProductDto;
import com.bnkcfplus.exercise.exceptions.ValidationException;
import com.bnkcfplus.exercise.model.Product;
import com.bnkcfplus.exercise.service.ProductService;
import com.bnkcfplus.exercise.utility.Utility;
import com.bnkcfplus.exercise.validator.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService service;

    @GetMapping(value = "/products")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> retrieveProducts() {
        return Optional.ofNullable(service)
                .map(ProductService::retrieveAllProducts)
                .filter(list -> !list.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(Utility.emptyTable());
    }

    @GetMapping(value = "/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> getProduct(@PathVariable(value = "id") long id) {
        return service.findProduct(id)
                .orElse(Utility.contentNotFound(id));
    }

    @PostMapping(value = "/products")
    public ResponseEntity<ProductDto> postProduct(@RequestBody Product product) throws ValidationException {
        ProductValidator.validatePostProduct(product);
        return service.saveProduct(product)
                .map(prod -> ResponseEntity.status(HttpStatus.CREATED).body(prod))
                .orElse(null);
    }

    @PutMapping(value = "/products")
    public ResponseEntity<ProductDto> putProduct(@RequestBody Product product) throws ValidationException {
        ProductValidator.validatePutProduct(product);
        return service.update(product)
                .map(ResponseEntity::ok)
                .orElse(null);
    }

    @ExceptionHandler({ValidationException.class, SQLException.class})
    public ResponseEntity<ProductDto> handle(Exception ex) {
        ex.printStackTrace();
        if (ex instanceof ValidationException) {
            log.error("error on validation");
            return ResponseEntity.badRequest().build();
        } else if (ex instanceof DataIntegrityViolationException) {
            log.error("sql error occurred : {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else if (ex instanceof SQLException) {
            log.error("sql error occurred : {} , sql state : {}", ex.getMessage(), ((SQLException) ex).getSQLState());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
        log.error("exception occurred : {}", ex.getMessage());
        return ResponseEntity.internalServerError().build();
    }
}
