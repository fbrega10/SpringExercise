package com.bnkcfplus.exercise.controller;


import com.bnkcfplus.exercise.dto.ProductDto;
import com.bnkcfplus.exercise.model.Product;
import com.bnkcfplus.exercise.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class ProductController {
    /*
    this is the main Controller class in which we filter our requests
    in their different methods
     */
    private final ProductService service;

    @GetMapping(value = "/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> retrieveProducts(){
        return Optional.ofNullable(service)
                .map(ProductService::retrieveAllProducts)
                .orElse(null);
    }

    @GetMapping(value = "/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getProduct(@PathVariable(value = "id") long id) throws Exception {
        return service.findProduct(id).orElse(null);
    }

    @PostMapping(value = "/product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto postProduct(@RequestBody Product product){
        return service.saveProduct(product).orElse(null);
    }
}
