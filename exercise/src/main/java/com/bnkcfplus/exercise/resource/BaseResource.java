package com.bnkcfplus.exercise.resource;

import com.bnkcfplus.exercise.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BaseResource extends ProductDto implements Serializable {
    @JsonProperty(value = "code")
    private String code;
    @JsonProperty(value = "status")
    private String status;
    @JsonProperty(value = "message")
    private String message;
}
