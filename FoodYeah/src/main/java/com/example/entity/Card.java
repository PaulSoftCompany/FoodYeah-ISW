package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {
    @ApiModelProperty(value="ID de la tarjeta", dataType="Long",  example="1", position=1)
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value="Usuario de la tarjeta", dataType="Customer", position=2)
    @NotNull(message = "El usuario no puede estar vacío")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Customer customer;

    @ApiModelProperty(value="Numero de la tarjeta", dataType="string",  example="1234567898765432", position=3)
    @NotNull(message = "El numero de la tarjeta no deber ser vacio")
    private String cardNumber;

    @ApiModelProperty(value="Tipo de tarjeta", dataType="boolean",  example="1", position=4)
    @NotNull(message = "El tipo de tarjeta no deber ser vacio")
    @Column(name = "card_type",nullable = false)
    private boolean cardType;

    @ApiModelProperty(value="El cvv o cvc de la tarjeta", dataType="byte",  example="1234", position=5)
    @NotNull(message = "El cvc o cvv de la tarjeta no deber ser vacio")
    @Column(name = "card_cvi",nullable = false)
    private int cardCvi;

    @ApiModelProperty(value="Fecha de expiracion de la tarjeta", dataType="String",  example="1234", position=6)
    @NotEmpty(message = "El cvc o cvv de la tarjeta no deber ser vacio")
    @Column(name = "card_expire_date",nullable = false)
    private String cardExpireDate;

    @ApiModelProperty(value="El nombre del usuario de la tarjeta", dataType="String",  example="Alexis Enrique Barrios Pérez", position=7)
    @Column(name = "card_owner_name")
    private String cardOwnerName;

    @ApiModelProperty(value="Ultima acción realizada por el usuario", dataType="String",  example="CREATED", position=8)
    @Column(name = "card_state")
    private String state;

    @ApiModelProperty(value="El dinero de la tarjeta", dataType="float",  example="100", position=9)
    @Column(name = "card_money")
    private float cardMoney;
}
