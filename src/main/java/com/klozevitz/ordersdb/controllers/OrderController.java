package com.klozevitz.ordersdb.controllers;

import com.klozevitz.ordersdb.model.dao.services.client.IDaoClient;
import com.klozevitz.ordersdb.model.dao.services.order.IDaoOrder;
import com.klozevitz.ordersdb.model.entities.client.Client;
import com.klozevitz.ordersdb.model.entities.order.Order;
import com.klozevitz.ordersdb.model.entities.order.OrderDTO;
import com.klozevitz.ordersdb.model.entities.order.OrderReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    IDaoOrder daoOrder;
    @Autowired
    IDaoClient daoClient;

    @GetMapping("/all")
    public List<OrderDTO> all() {
        List<OrderDTO> dto = new LinkedList<>();
        for (Order order: daoOrder.findAll())
            dto.add(new OrderDTO(order));
        return dto;
    }

    @GetMapping("/findById")
    public OrderDTO findById(@RequestParam int id) {
        return new OrderDTO(daoOrder.findById(id));
    }

    @PostMapping("/save")
    public OrderDTO save(@RequestParam int clientId, @RequestParam String description) {
        return new OrderDTO(daoOrder.save(new Order(description, daoClient.findById(clientId))));
    }

    @PostMapping("/update")
    public OrderDTO update(@RequestParam Integer id, @RequestParam (required = false) String description) {
        return new OrderDTO(daoOrder.update(new Order(id, description, new Client())));
    }

    @GetMapping("/delete")
    public OrderDTO delete(@RequestParam Integer id) {
        return new OrderDTO(daoOrder.delete(id));
    }

    @GetMapping("/info")
    public OrderReportDTO orderInfo(@RequestParam int id) {
        return daoOrder.orderInfo(id);
    }

    @GetMapping("/check")
    public List<String> check(@RequestParam int id) {
        return daoOrder.check(id);
    }
}
