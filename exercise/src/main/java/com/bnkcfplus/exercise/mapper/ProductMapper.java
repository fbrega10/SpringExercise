package com.bnkcfplus.exercise.mapper;

import com.bnkcfplus.exercise.dto.ProductDto;
import com.bnkcfplus.exercise.entity.ProductEntity;
import com.bnkcfplus.exercise.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto mapToDto(ProductEntity product);
    ProductEntity mapToEntity(Product product);

}
