package com.klozevitz.ordersdb.model.dao.services.order;

import com.klozevitz.ordersdb.model.entities.item.Item;
import com.klozevitz.ordersdb.model.entities.item.ItemDTO;
import com.klozevitz.ordersdb.model.entities.order.Order;
import com.klozevitz.ordersdb.model.entities.order.OrderReportDTO;
import com.klozevitz.ordersdb.model.entities.ordersItems.OrdersItems;
import com.klozevitz.ordersdb.model.repositories.IClientRepository;
import com.klozevitz.ordersdb.model.repositories.IItemRepository;
import com.klozevitz.ordersdb.model.repositories.IOrderRepository;
import com.klozevitz.ordersdb.model.repositories.IOrdersItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;


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
       return orderToUpdate;
    }

    @Override
    public Order delete(Integer id) {
        Order order = orderRep.findById(id).orElse(null);
        if (order == null)
            return new Order();
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

    public OrderReportDTO orderInfo(int id) {
        Order order = orderRep.findById(id).orElse(null);
        if (order == null)
            return  new OrderReportDTO();
        OrderReportDTO orderReportDTO = new OrderReportDTO(order);
//        Map<String, Integer> map = new HashMap<>();
        for (OrdersItems oi: oiRep.findAllByOrder(order)) {
            ItemDTO item = new ItemDTO(itemRep.findById(oi.getItem().getId()).orElse(new Item()));
            orderReportDTO.getItems().put(item.getId().toString().concat(": ").concat(item.getItemName()),
                                            oi.getQuantity());
        }
//        orderReportDTO.setItems(map);
        return orderReportDTO;
    }

    @Override
    public List<String> check(int id) {
        Order order = orderRep.findById(id).orElse(null);
        if (order == null)
            return new LinkedList<>();
        int finalPrice = 0;
        List<String> list = new LinkedList<>();
        for (OrdersItems oi: oiRep.findAllByOrder(order)) {
            finalPrice += oi.getItem().getPrice() * oi.getQuantity();
            ItemDTO item = new ItemDTO(itemRep.findById(oi.getItem().getId()).orElse(new Item()));
            list.add(item.getItemName().concat(": ").concat(String.valueOf(oi.getQuantity()).concat(" items")));
        }
        list.add("Final price: ".concat(String.valueOf(finalPrice)));
        return list;
    }
}



//    public Map<String, Integer> check(int id) {
//        Order order = orderRep.findById(id).orElse(null);
//        if (order == null)
//            return new HashMap<>();
//        int finalPrice = 0;
//        Map<String, Integer> map = new HashMap<>();
//        for (OrdersItems oi: oiRep.findAllByOrder(order)) {
//            finalPrice += oi.getItem().getPrice() * oi.getQuantity();
//            ItemDTO item = new ItemDTO(itemRep.findById(oi.getItem().getId()).orElse(new Item()));
//            map.put(item.getItemName(), oi.getQuantity());
//        }
//        map.put("Final price: ", finalPrice);
//        System.out.println(map);
//        return map;
//    }