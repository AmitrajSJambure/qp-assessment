package com.grocery.booking.Grocery.dao.dto;

import com.grocery.booking.Grocery.util.MapConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.NamedEntityGraph;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String name;
    private double orderPrice;
    private Map<String, Integer> items;
}
