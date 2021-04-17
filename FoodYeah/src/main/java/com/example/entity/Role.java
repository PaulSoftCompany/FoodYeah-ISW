package com.example.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="roles")
public class Role {
    @ApiModelProperty(value="ID del rol", dataType="Long", position=1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value="El nombre del rol", dataType="String", position=2)
    @NotEmpty(message = "El nombre no puede ser vacio ")
    @Column(name = "role_name", nullable = false)
    private String roleName;

    @ApiModelProperty(value="La descripción del rol", dataType="String", position=3)
    @Column(name = "role_description")
    private String roleDescription;

    @ApiModelProperty(value="Ultima acción realizada por el usuario", dataType="String",  example="CREATED", position=4)
    @Column(name = "role_state")
    public String state;
}