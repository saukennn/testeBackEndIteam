package com.example.testeBackEnd.controllers;

import com.example.testeBackEnd.dtos.ProductDTO;
import com.example.testeBackEnd.mappers.ProductMapper;
import com.example.testeBackEnd.models.Product;
import com.example.testeBackEnd.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Validated @RequestBody ProductDTO productDTO) {
        Product product = ProductMapper.toEntity(productDTO);
        Product createdProduct = productService.createProduct(product);
        ProductDTO createdProductDTO = ProductMapper.toDTO(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOs = products.stream()
                .map(ProductMapper::toDTO)
                .toList();
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        ProductDTO productDTO = ProductMapper.toDTO(product);
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Validated @RequestBody ProductDTO productDTO) {
        Product product = ProductMapper.toEntity(productDTO);
        Product updatedProduct = productService.updateProduct(id, product);
        ProductDTO updatedProductDTO = ProductMapper.toDTO(updatedProduct);
        return ResponseEntity.ok(updatedProductDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
