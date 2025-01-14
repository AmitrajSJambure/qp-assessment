package com.grocery.booking.Grocery.services;

import com.grocery.booking.Grocery.dao.dto.Grocery;
import com.grocery.booking.Grocery.dao.dto.Order;
import com.grocery.booking.Grocery.dao.entity.OrderEntity;
import com.grocery.booking.Grocery.exception.GroceryException;
import com.grocery.booking.Grocery.repository.OrderRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class BookingService {
    private final OrderRepo orderRepo;
    private final GroceryService groceryService;

    @Autowired
    public BookingService(OrderRepo orderRepo, GroceryService groceryService) {
        this.orderRepo = orderRepo;
        this.groceryService = groceryService;
    }

    @Transactional
    public void createOrder(Map<String, Integer> input, String user) {
        if (orderRepo.findByName(user) != null) {
            log.error("Order for user {} already exists", user);
            throw new GroceryException(String.format("Order for user %s already exists", user));
        }
        double totalPrice = 0.0;
        Map<String, Integer> itemsBought = new HashMap<>();
        for (Map.Entry<String, Integer> entry : input.entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            Grocery grocery = groceryService.getGroceryByName(k);
            if (grocery.getQuantity() >= v) {
                totalPrice += (grocery.getPrice() * v);
                grocery.setQuantity(grocery.getQuantity() - v);
                updateGrocery(grocery);
                itemsBought.put(k, v);
            } else {
                log.error("Item {} is less than requested", k);
                throw new GroceryException(String.format("Item %s is less than requested", k));
            }
        }
        if(totalPrice >= 0) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setName(user);
            orderEntity.setOrderPrice(totalPrice);
            orderEntity.setItems(itemsBought);
            orderRepo.saveAndFlush(orderEntity);
        }
    }

    private void updateGrocery(Grocery grocery) {
        if(grocery.getQuantity() <= 0) {
            groceryService.deleteByName(grocery.getName());
        } else {
            groceryService.updateGrocery(grocery);
        }
    }

    public Order getOrderByName(String name) {
        OrderEntity orderEntity = orderRepo.findByName(name);
        if(orderEntity == null) {
            log.error("No order available for user {}", name);
            throw new GroceryException(String.format("No order available for user %s", name));
        }
        return new Order(orderEntity.getName(), orderEntity.getOrderPrice(), orderEntity.getItems());
    }

    public void deleteOrderByName(String name) {
        OrderEntity orderEntity = orderRepo.findByName(name);
        if(orderEntity == null) {
            log.info("No order available for user {}", name);

        }
       else {
           orderRepo.delete(orderEntity);
        }
    }
}
