package com.grocery.booking.Grocery.controllers;


import com.grocery.booking.Grocery.dao.dto.Grocery;
import com.grocery.booking.Grocery.dao.dto.Order;
import com.grocery.booking.Grocery.services.BookingService;
import com.grocery.booking.Grocery.services.GroceryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/grocery")
public class GroceryController {

    private final GroceryService groceryService;
    private final BookingService bookingService;

    @Autowired
    public GroceryController(GroceryService groceryService, BookingService bookingService) {
        this.groceryService = groceryService;
        this.bookingService = bookingService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Grocery> addNewItem(@RequestBody Grocery input) {
        Grocery grocery = groceryService.addNewGrocery(input);
        log.info("{} added to groceries", input.getName());
        return new ResponseEntity<>(grocery, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Grocery>> getAllGroceries() {
        List<Grocery> groceries = groceryService.getAllGrocery();
        return new ResponseEntity<>(groceries, HttpStatus.OK);
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<Grocery> getGroceryByName(@PathVariable(value = "name") String name) {
        Grocery grocery = groceryService.getGroceryByName(name);
        return new ResponseEntity<>(grocery, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteAllGroceries() {
        groceryService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteByName(@PathVariable(value = "name") String name) {
        groceryService.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Grocery> updateGroceryItem(@RequestBody Grocery input) {
        Grocery grocery = groceryService.updateGrocery(input);
        log.info("{} updated", input.getName());
        return new ResponseEntity<>(grocery, HttpStatus.OK);
    }

    @PutMapping("/book")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity bookGroceries(@RequestBody Map<String, Integer> input,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        bookingService.createOrder(input, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/book/get/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> getOrdersByName(@PathVariable String name) {
        Order order = bookingService.getOrderByName(name);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/book/delete/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteOrderForUser(@PathVariable String name) {
        bookingService.deleteOrderByName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
