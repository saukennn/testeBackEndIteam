package com.example.testeBackEnd.dtos;

import jakarta.validation.constraints.*;
import lombok.*;


import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Product name is mandatory")
    private String name;

    private String description;

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @Min(value = 0, message = "Stock amount cannot be negative")
    private Integer stockAmount;
}
