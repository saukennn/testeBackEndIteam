package com.example.testeBackEnd.mappers;

import com.example.testeBackEnd.dtos.ProductDTO;
import com.example.testeBackEnd.models.Product;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockAmount()
        );
    }

    public static Product toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        return new Product(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getStockAmount(),
                null, // createDate e updateDate serão setados automaticamente
                null
        );
    }
}
