package com.klozevitz.ordersdb.model.repositories;

import com.klozevitz.ordersdb.model.entities.client.Client;
import com.klozevitz.ordersdb.model.entities.order.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends CrudRepository<Order, Integer> {
    @Override
    Optional<Order> findById(Integer integer);

    List<Order> findAllByClient(Client client);
}
