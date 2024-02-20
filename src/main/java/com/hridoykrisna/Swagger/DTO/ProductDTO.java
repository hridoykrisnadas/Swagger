package com.hridoykrisna.Swagger.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductDTO {
    private long id;
    @NotBlank(message = "Name is Mandatory")
    private String name;
    @Min(value = 1, message = "Price Must Mandatory")
    private float price;
}
