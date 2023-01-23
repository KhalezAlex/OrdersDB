package com.klozevitz.ordersdb.model.repositories;

import com.klozevitz.ordersdb.model.entities.item.Item;
import com.klozevitz.ordersdb.model.entities.order.Order;
import com.klozevitz.ordersdb.model.entities.ordersItems.OrdersItems;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IOrdersItemsRepository extends CrudRepository<OrdersItems, Integer> {
    @Override
    Optional<OrdersItems> findById(Integer id);

    List<OrdersItems> findAllByItem(Item item);

    List<OrdersItems> findAllByOrder(Order order);
}
