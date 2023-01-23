package com.klozevitz.ordersdb.model.repositories;

import com.klozevitz.ordersdb.model.entities.item.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IItemRepository extends CrudRepository<Item, Integer>{
    @Override
    Optional<Item> findById(Integer integer);
}
