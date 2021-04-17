package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="customers")
public class Customer {
    @ApiModelProperty(value="ID del usuario", dataType="Long", position=1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value="Categoria del usuario", dataType="Customer_Category", position=2)
    @NotNull(message = "La categoría no puede ser vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private CustomerCategory customerCategory;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "customer_roles",
            joinColumns = @JoinColumn(name= "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name= "role_id", referencedColumnName = "id")
        )
    private List<Role> customerRoles;

    @ApiModelProperty(value="El nombre del usuario", dataType="String", position=4)
    @NotEmpty(message = "El nombre no puede ser vacio ")
    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @ApiModelProperty(value="La edad del usuario", dataType="byte", position=5)
    @Column(name = "customer_age", nullable = true)
    @NotNull(message = "No puede estar vacio")
    private byte customerAge;

    @ApiModelProperty(value="El nombre de la cuenta del usuario", dataType="String", position=6)
    @NotEmpty(message = "El nombre de la cuenta del usuario no puede ser vacio ")
    @Column(name = "customer_username", nullable = false)
    private String username;

    @ApiModelProperty(value="La contraseña de la cuenta del usuario", dataType="String", position=7)
    @NotEmpty(message = "La contraseña de la cuenta del usuario no puede ser vacio ")
    @Column(name = "customer_password", nullable = false)
    private String password;

    @ApiModelProperty(value="Ultima acción realizada por el usuario", dataType="String",  example="CREATED", position=8)
    @Column(name = "customer_state")
    public String state;
}
