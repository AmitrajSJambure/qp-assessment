package com.grocery.booking.Grocery.services;

import com.grocery.booking.Grocery.dao.dto.Grocery;
import com.grocery.booking.Grocery.exception.GroceryException;
import com.grocery.booking.Grocery.util.GroceryUtil;
import com.grocery.booking.Grocery.dao.entity.GroceryEntity;
import com.grocery.booking.Grocery.repository.GroceryRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class GroceryService {

    private final GroceryRepo groceryRepo;

    @Autowired
    public GroceryService(GroceryRepo groceryRepo) {
        this.groceryRepo = groceryRepo;
    }

    @Transactional
    public Grocery addNewGrocery(Grocery input) {
        GroceryEntity groceryEntity = groceryRepo.findByName(input.getName());
        if (groceryEntity != null) {
            log.error("{} already exists", input.getName());
            throw new GroceryException(String.format("%s already exists", input.getName()));
        }
        groceryEntity = groceryRepo.saveAndFlush(GroceryUtil.toEntity(groceryEntity, input));
        return GroceryUtil.toDTO(groceryEntity);
    }

    public List<Grocery> getAllGrocery() {
        List<GroceryEntity> groceryEntities = groceryRepo.findAll();
        List<Grocery> groceries = new ArrayList<>();
        if (!CollectionUtils.isEmpty(groceryEntities)) {
            groceryEntities.forEach(entity -> groceries.add(GroceryUtil.toDTO(entity)));
        }
        return groceries;
    }

    public Grocery getGroceryByName(String name) {
        GroceryEntity groceryEntity = groceryRepo.findByName(name);
        if (groceryEntity == null) {
            log.error("{} does not exists", name);
            // need to throw exception
            throw new GroceryException(String.format("%s doesn't exists", name));
        }
        return GroceryUtil.toDTO(groceryEntity);
    }

    @Transactional
    public void deleteAll() {
        groceryRepo.deleteAll();
    }

    @Transactional
    public void deleteByName(String name) {
        GroceryEntity groceryEntity = groceryRepo.findByName(name);
        if (groceryEntity != null) {
            groceryRepo.delete(groceryEntity);
        }
    }

    @Transactional
    public Grocery updateGrocery(Grocery input) {
        GroceryEntity groceryEntity = groceryRepo.findByName(input.getName());
        if (groceryEntity == null) {
            log.error("{} doesn't exists", input.getName());
            throw new GroceryException(String.format("%s doesn't exists", input.getName()));
        }
        groceryEntity = groceryRepo.saveAndFlush(GroceryUtil.toEntity(groceryEntity, input));
        return GroceryUtil.toDTO(groceryEntity);
    }

}
