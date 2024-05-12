package com.bnkcfplus.exercise.utility;

import com.bnkcfplus.exercise.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class Utility {

    private static final Logger log = LoggerFactory.getLogger(Utility.class);

    public static ResponseEntity<ProductDto> contentNotFound(long id){
        log.error("could not find product id : {}", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public static ResponseEntity<List<ProductDto>>  emptyTable(){
       log.error("empty table CFPLUS_PRODUCTS, no element found");
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
