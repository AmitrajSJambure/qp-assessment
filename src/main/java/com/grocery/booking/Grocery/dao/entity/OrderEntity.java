package com.grocery.booking.Grocery.dao.entity;

import com.grocery.booking.Grocery.util.MapConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Table(name = "BOOKING_ORDER")
@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "ORDER_PRICE")
    private double orderPrice;

    @Convert(converter = MapConverter.class)
    @Column(name = "ITEMS", length = 4000)
    private Map<String, Integer> items;
}
