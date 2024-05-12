package com.bnkcfplus.exercise.controller;


import com.bnkcfplus.exercise.dto.ProductDto;
import com.bnkcfplus.exercise.exceptions.NoIdException;
import com.bnkcfplus.exercise.model.Product;
import com.bnkcfplus.exercise.service.ProductService;
import com.bnkcfplus.exercise.utility.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class ProductController {

    private final ProductService service;

    @GetMapping(value = "/products")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> retrieveProducts() {
        return Optional.ofNullable(service)
                .map(ProductService::retrieveAllProducts)
                .map(ResponseEntity::ok)
                .orElse(Utility.emptyTable());
    }

    @GetMapping(value = "/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> getProduct(@PathVariable(value = "id") long id) {
        return service.findProduct(id).orElse(Utility.contentNotFound(id));
    }

    @PostMapping(value = "/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto postProduct(@RequestBody Product product) {
        return service.saveProduct(product).orElse(null);
    }

    @PutMapping(value = "/products")
    public ResponseEntity<ProductDto> putProduct(@RequestBody Product product) throws NoIdException {
        try {
            return service.update(product).map(ResponseEntity::ok).orElse(null);
        } catch (NoIdException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
