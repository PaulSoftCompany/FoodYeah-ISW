package com.example.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "customer_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerCategory {

    @ApiModelProperty(value = "ID de la categoria de usuarios", dataType = "Long", position = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "Nombre de la categoría", dataType = "String", position = 2)
    @NotEmpty(message = "El nombre no puede ser vacio")
    @Column(name = "customer_category_name", nullable = false)
    private String customerCategoryName;

    @ApiModelProperty(value = "Descripción de la categoría", dataType = "String", position = 3)
    @NotEmpty(message = "La descripción no puede ser vacía")
    @Column(name = "customer_category_description", nullable = false)
    private String customerCategoryDescription;

    @ApiModelProperty(value = "Ultima acción realizada por el usuario", dataType = "String", example = "CREATED", position = 4)
    @Column(name = "customer_category_state")
    public String state;
}
