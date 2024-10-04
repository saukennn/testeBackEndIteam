package com.example.testeBackEnd.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.Data; // Importando @Data do Lombok
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data // Essa anotação gera automaticamente getters, setters, toString, equals e hashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products") // Use plural para seguir a convenção
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Product name is mandatory")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @Column(name = "stock_amount") // Nome da coluna seguindo a convenção
    @Min(value = 0, message = "Stock amount cannot be negative")
    private Integer stockAmount;

    @Column(name = "create_date", updatable = false)
    private OffsetDateTime createDate;

    @Column(name = "update_date")
    private OffsetDateTime updateDate;

    @PrePersist
    protected void onCreate() {
        this.createDate = OffsetDateTime.now();
        this.updateDate = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = OffsetDateTime.now();
    }
}
