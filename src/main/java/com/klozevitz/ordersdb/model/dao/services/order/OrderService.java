package com.klozevitz.ordersdb.model.dao.services.order;

import com.klozevitz.ordersdb.model.entities.order.Order;
import com.klozevitz.ordersdb.model.entities.ordersItems.OrdersItems;
import com.klozevitz.ordersdb.model.repositories.IClientRepository;
import com.klozevitz.ordersdb.model.repositories.IItemRepository;
import com.klozevitz.ordersdb.model.repositories.IOrderRepository;
import com.klozevitz.ordersdb.model.repositories.IOrdersItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService implements IDaoOrder {
    @Autowired
    IOrderRepository orderRep;
    @Autowired
    IClientRepository clientRep;
    @Autowired
    IOrdersItemsRepository oiRep;
    @Autowired
    IItemRepository itemRep;

    @Override
    public List<Order> findAll() {
        return (List<Order>) orderRep.findAll();
    }

    @Override
    public Order findById(Integer id) {
        return orderRep.findById(id).orElse(new Order());
    }

    @Override
    public Order save(Order order) {
        if (clientRep.findById(order.getClient().getId()).isEmpty())
            return new Order();
        Order orderSaved = orderRep.save(order);
        clientRep.findById(order.getClient().getId()).get().getOrders().add(orderSaved);
        return orderSaved;
    }

    @Transactional
    @Override
    public Order update(Order order) {
        if (orderRep.findById(order.getId()).isEmpty())
            return new Order();
        return updateOrder(order);
    }

    private Order updateOrder(Order order) {
        Order orderToUpdate = orderRep.findById(order.getId()).orElse(null);
        assert orderToUpdate != null;
        orderToUpdate.setDescription(order.getDescription());
        updateClient(orderToUpdate, order);
        return orderToUpdate;
    }

    private void updateClient(Order orderToUpdate, Order order) {
        if (Objects.equals(orderToUpdate.getClient().getId(), order.getClient().getId()))
            return;
        clientRep.findById(orderToUpdate.getClient().getId()).get().getOrders().remove(orderToUpdate);
        orderToUpdate.setClient(order.getClient());
        clientRep.findById(order.getClient().getId()).get().getOrders().add(orderToUpdate);
    }

    @Override
    public Order delete(Integer id) {
        Order order = orderRep.findById(id).orElse(null);
        if (order == null)
            return new Order();
        System.out.println("*******************************************");
        clientRep.findById(order.getClient().getId()).get().getOrders().remove(order);
        deleteOrdersItems(order);
        orderRep.deleteById(id);
        return order;
    }

    private void deleteOrdersItems(Order order) {
        for (OrdersItems oi: oiRep.findAllByOrder(order)) {
            itemRep.findById(oi.getItem().getId()).get().getOrderItems().remove(oi);
            oiRep.delete(oi);
        }
    }
}
