package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@Builder
@Table(name="orders")
public class Order{

    @ApiModelProperty(value="ID de la orden", dataType="Long", position=1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value="El usuario de la orden", dataType="Customer", position=2)
    @NotNull(message = "El usuario de la orden no puede estar vacío")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Customer costumer;

    @ApiModelProperty(value="detalle de la orden", dataType="List<OrderDetail>", position=3)
    @Valid
    @NotNull(message = "no puede estar vacío")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @ApiModelProperty(value="El nombre del usuario", dataType="String", position=4)
    @Column(name = "Date")
    private String date;

    @ApiModelProperty(value="El tiempo de inicio del pedido", dataType="String", position=5)
    @Column(name = "init_time")
    private String inittime;

    @ApiModelProperty(value="El tiempo de finalización del pedido", dataType="String", position=6)
    @Column(name = "end_Time")
    private String endtime;

    @ApiModelProperty(value="El precio total de la orden", dataType="float", position=7)
    @Column(name = "total_price")
    private float totalPrice;

    @ApiModelProperty(value="Ultima acción realizada por el usuario", dataType="String",  example="CREATED", position=8)
    @Column(name = "order_state")
    public String state;

    public Order() {
        orderDetails = new ArrayList<>();
    }
}