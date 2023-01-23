package com.klozevitz.ordersdb.controllers;

import com.klozevitz.ordersdb.model.dao.services.client.IDaoClient;
import com.klozevitz.ordersdb.model.dao.services.order.IDaoOrder;
import com.klozevitz.ordersdb.model.entities.order.Order;
import com.klozevitz.ordersdb.model.entities.order.OrderDTO;
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

//    @PostMapping("/update")
//    public Order update(@RequestParam Integer orderId, @RequestParam (required = false) String description,
//                        @RequestParam (required = false) Integer clientId) {
//        if (daoOrder.findById(orderId).isEmpty())
//            return null;
//        Order order = daoOrder.findById(orderId).get();
//        if (description != null)
//            order.setDescription(description);
//        if (clientId != null)
//            if (daoClient.findById(clientId).isPresent())
//                order.setClient(daoClient.findById(clientId).get());
//        return daoOrder.update(order);
//    }

    @GetMapping("/delete")
    public OrderDTO delete(@RequestParam Integer id) {
        return new OrderDTO(daoOrder.delete(id));
    }
}
