package com.bnkcfplus.exercise.service;

import com.bnkcfplus.exercise.dto.ProductDto;
import com.bnkcfplus.exercise.mapper.ProductMapper;
import com.bnkcfplus.exercise.model.Product;
import com.bnkcfplus.exercise.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    public List<ProductDto> retrieveAllProducts() {
        return Optional.ofNullable(repository)
                .map(ProductRepository::findAll)
                .orElse(Collections.emptyList())
                .stream().map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<ResponseEntity<ProductDto>> findProduct(long id) {
        return Optional.of(id)
                .map(repository::findById)
                .orElse(null)
                .map(mapper::mapToDto)
                .map(ResponseEntity::ok);
    }

    public Optional<ProductDto> saveProduct(Product product) {
        return Optional.of(product)
                .map(mapper::mapToEntity)
                .map(repository::save)
                .map(mapper::mapToDto);
    }

    public Optional<ProductDto> update(Product product) {
        return Optional.of(product)
                .map(Product::getId)
                .map(repository::findById)
                .filter(Objects::nonNull)
                .map(prod -> mapper.mapToEntity(product))
                .map(repository::save)
                .map(mapper::mapToDto);
    }
}
