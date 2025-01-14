package com.grocery.booking.Grocery.util;

import com.grocery.booking.Grocery.dao.dto.Grocery;
import com.grocery.booking.Grocery.dao.entity.GroceryEntity;

public class GroceryUtil {
    public static Grocery toDTO(GroceryEntity groceryEntity) {
        return new Grocery(groceryEntity.getName(), groceryEntity.getPrice(), groceryEntity.getQuantity());
    }

    public static GroceryEntity toEntity(GroceryEntity groceryEntity, Grocery grocery) {
        if(groceryEntity == null) {
            groceryEntity = new GroceryEntity();
        }
        groceryEntity.setName(grocery.getName());
        groceryEntity.setPrice(grocery.getPrice());
        groceryEntity.setQuantity(grocery.getQuantity());
        return groceryEntity;
    }
}
