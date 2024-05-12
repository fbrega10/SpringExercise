package com.bnkcfplus.exercise.service;

import com.bnkcfplus.exercise.entity.ProductEntity;
import com.bnkcfplus.exercise.mapper.ProductMapper;
import com.bnkcfplus.exercise.model.Product;
import com.bnkcfplus.exercise.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService service;
    @Mock
    private ProductRepository repository;

    @BeforeEach
    void testSetup() {
        ReflectionTestUtils.setField(service, "mapper", Mappers.getMapper(ProductMapper.class));
    }

    @Test
    void retrieveAllProducts() {
        Mockito.when(repository.findAll()).thenReturn(Collections.nCopies(3, new ProductEntity()));
        Assertions.assertEquals(3, service.retrieveAllProducts().size());
    }

    @Test
    void findProduct() {
        Mockito.when(repository.findById(23L)).thenReturn(Optional.of(new ProductEntity()));
        Assertions.assertNotNull(service.findProduct(23L));
    }

    @Test
    void saveProduct() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(new ProductEntity());
        Assertions.assertNotNull(service.saveProduct(new Product()));
    }

    @Test
    void update() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(new ProductEntity());
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(new ProductEntity()));
        Assertions.assertNotNull(service.update(new Product(12L, "financial", BigDecimal.TEN)));
    }
}