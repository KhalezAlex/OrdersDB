package com.klozevitz.ordersdb.controllers;

import com.klozevitz.ordersdb.model.dao.services.client.IDaoClient;
import com.klozevitz.ordersdb.model.dao.services.item.IDaoItem;
import com.klozevitz.ordersdb.model.dao.services.order.IDaoOrder;
import com.klozevitz.ordersdb.model.dao.services.ordersItems.IDaoOrdersItems;
import com.klozevitz.ordersdb.model.entities.client.Client;
import com.klozevitz.ordersdb.model.entities.item.Item;
import com.klozevitz.ordersdb.model.entities.order.Order;
import com.klozevitz.ordersdb.model.entities.ordersItems.OrdersItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    IDaoClient daoClient;

    @Autowired
    IDaoItem daoItem;

    @Autowired
    IDaoOrder daoOrder;

    @Autowired
    IDaoOrdersItems daoOI;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/generateBase")
    public String generateBase() {
        generateClients();
        generateItems();
        generateOrders();
        generateOrderItems();
        return "done";
    }

    private void generateClients() {
        daoClient.save(new Client("Bob"));
        daoClient.save(new Client("Rick"));
        daoClient.save(new Client("Tom"));
        daoClient.save(new Client("Ronald"));
    }

    private void generateItems() {
        daoItem.save(new Item("Dendy", 1L, 1500));
        daoItem.save(new Item("Sega", 2L, 2000));
        daoItem.save(new Item("GameBoy", 3L, 5000));
        daoItem.save(new Item("PS1", 4L, 10000));
        daoItem.save(new Item("PS2", 5L, 14000));
        daoItem.save(new Item("PS3", 6L, 20000));
        daoItem.save(new Item("PS4", 7L, 33000));
        daoItem.save(new Item("PS5", 8L, 60000));
        daoItem.save(new Item("X-BOX", 9L, 65000));
        daoItem.save(new Item("Atari", 10L, 100000));
    }

    private void generateOrders() {
        daoOrder.save(new Order("to Moscow", daoClient.findById(1)));
        daoOrder.save(new Order("to Moscow", daoClient.findById(1)));
        daoOrder.save(new Order("to SPB", daoClient.findById(2)));
        daoOrder.save(new Order("to EKB", daoClient.findById(3)));
        daoOrder.save(new Order("to EKB", daoClient.findById(3)));
        daoOrder.save(new Order("to EKB", daoClient.findById(3)));
    }

    private void generateOrderItems() {
        setOrder1();
        setOrder2();
        setOrder3();
        setOrder4();
        setOrder5();
        setOrder6();
    }

    private void setOrder1() {
        daoOI.save(new OrdersItems(1, daoItem.findById(1), daoOrder.findById(1)));
        daoOI.save(new OrdersItems(3, daoItem.findById(2), daoOrder.findById(1)));
        daoOI.save(new OrdersItems(1, daoItem.findById(4), daoOrder.findById(1)));
        daoOI.save(new OrdersItems(20, daoItem.findById(8), daoOrder.findById(1)));
    }

    private void setOrder2() {
        daoOI.save(new OrdersItems(3, daoItem.findById(9), daoOrder.findById(2)));
        daoOI.save(new OrdersItems(15, daoItem.findById(3), daoOrder.findById(2)));
        daoOI.save(new OrdersItems(10, daoItem.findById(2), daoOrder.findById(2)));
        daoOI.save(new OrdersItems(1, daoItem.findById(10), daoOrder.findById(2)));
    }

    private void setOrder3() {
        daoOI.save(new OrdersItems(7, daoItem.findById(4), daoOrder.findById(3)));
        daoOI.save(new OrdersItems(9, daoItem.findById(7), daoOrder.findById(3)));
        daoOI.save(new OrdersItems(1, daoItem.findById(5), daoOrder.findById(3)));
        daoOI.save(new OrdersItems(23, daoItem.findById(9), daoOrder.findById(3)));
    }

    private void setOrder4() {
        daoOI.save(new OrdersItems(35, daoItem.findById(8), daoOrder.findById(4)));
        daoOI.save(new OrdersItems(30, daoItem.findById(3), daoOrder.findById(4)));
        daoOI.save(new OrdersItems(60, daoItem.findById(9), daoOrder.findById(4)));
        daoOI.save(new OrdersItems(23, daoItem.findById(2), daoOrder.findById(4)));
        daoOI.save(new OrdersItems(11, daoItem.findById(7), daoOrder.findById(4)));
    }

    private void setOrder5() {
        daoOI.save(new OrdersItems(1, daoItem.findById(10), daoOrder.findById(5)));
        daoOI.save(new OrdersItems(1, daoItem.findById(3), daoOrder.findById(5)));
    }

    private void setOrder6() {
        daoOI.save(new OrdersItems(1, daoItem.findById(1), daoOrder.findById(6)));
        daoOI.save(new OrdersItems(1, daoItem.findById(2), daoOrder.findById(6)));
        daoOI.save(new OrdersItems(1, daoItem.findById(3), daoOrder.findById(6)));
        daoOI.save(new OrdersItems(1, daoItem.findById(4), daoOrder.findById(6)));
        daoOI.save(new OrdersItems(1, daoItem.findById(5), daoOrder.findById(6)));
        daoOI.save(new OrdersItems(1, daoItem.findById(6), daoOrder.findById(6)));
        daoOI.save(new OrdersItems(1, daoItem.findById(7), daoOrder.findById(6)));
        daoOI.save(new OrdersItems(1, daoItem.findById(8), daoOrder.findById(6)));
        daoOI.save(new OrdersItems(1, daoItem.findById(9), daoOrder.findById(6)));
        daoOI.save(new OrdersItems(1, daoItem.findById(10), daoOrder.findById(6)));
    }
}
