package com.klozevitz.ordersdb.controllers;

import com.klozevitz.ordersdb.model.dao.services.item.IDaoItem;
import com.klozevitz.ordersdb.model.dao.services.order.IDaoOrder;
import com.klozevitz.ordersdb.model.dao.services.ordersItems.IDaoOrdersItems;
import com.klozevitz.ordersdb.model.entities.ordersItems.OrdersItems;
import com.klozevitz.ordersdb.model.entities.ordersItems.OrdersItemsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(path = "/ordersItems")
public class OrdersItemsController {
    @Autowired
    IDaoOrdersItems daoOI;

    @Autowired
    IDaoItem daoItem;

    @Autowired
    IDaoOrder daoOrder;


    @GetMapping("/all")
    public List<OrdersItemsDTO> all() {
        List<OrdersItemsDTO> dto = new LinkedList<>();
        for (OrdersItems oi: daoOI.findAll())
            dto.add(new OrdersItemsDTO(oi));
        return dto;
    }

    @GetMapping("/findById")
    public OrdersItemsDTO findById(@RequestParam int id) {
        return new OrdersItemsDTO(daoOI.findById(id));
    }

    @PostMapping("/save")
    public OrdersItemsDTO save(@RequestParam int quantity, @RequestParam int itemId,
                               @RequestParam int orderId) {
        return new OrdersItemsDTO(daoOI.save(
                                    new OrdersItems(quantity,
                                            daoItem.findById(itemId), daoOrder.findById(orderId))));
    }



    @PostMapping("/delete")
    public OrdersItemsDTO delete(@RequestParam int id) {
        return new OrdersItemsDTO(daoOI.delete(id));
    }

}
