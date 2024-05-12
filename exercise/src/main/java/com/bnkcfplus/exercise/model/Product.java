package com.bnkcfplus.exercise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "price")
    @DecimalMin(inclusive = false, value = "0.00")
    @DecimalMax(inclusive = false, value = "5000000.00")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
