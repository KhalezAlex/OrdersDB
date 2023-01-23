package com.klozevitz.ordersdb.model.dao.services.ordersItems;

import com.klozevitz.ordersdb.model.entities.item.Item;
import com.klozevitz.ordersdb.model.entities.order.Order;
import com.klozevitz.ordersdb.model.entities.ordersItems.OrdersItems;
import com.klozevitz.ordersdb.model.repositories.IItemRepository;
import com.klozevitz.ordersdb.model.repositories.IOrderRepository;
import com.klozevitz.ordersdb.model.repositories.IOrdersItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class OrdersItemsService implements IDaoOrdersItems {
    @Autowired
    IOrdersItemsRepository oiRep;
    @Autowired
    IItemRepository itemRep;
    @Autowired
    IOrderRepository orderRep;

    @Override
    public List<OrdersItems> findAll() {
        return (LinkedList<OrdersItems>) oiRep.findAll();
    }

    @Override
    public OrdersItems findById(Integer id) {
        return oiRep.findById(id).orElse(new OrdersItems());
    }

    @Override
    public OrdersItems save(OrdersItems ordersItems) {
        if (itemRep.findById(ordersItems.getItem().getId()).isEmpty() ||
                orderRep.findById(ordersItems.getOrder().getId()).isEmpty())
            return new OrdersItems();
        OrdersItems oiSaved = oiRep.save(ordersItems);
        itemRep.findById(ordersItems.getItem().getId()).get().getOrderItems().add(oiSaved);
        orderRep.findById(ordersItems.getOrder().getId()).get().getOrderItems().add(oiSaved);
        return oiSaved;
    }

    @Transactional
    @Override
    public OrdersItems update(OrdersItems oi) {
        if (oiRep.findById(oi.getId()).isEmpty())
            return new OrdersItems();
        return updateOI(oi);
    }

    private OrdersItems updateOI(OrdersItems oi) {
        OrdersItems oiToUpdate = oiRep.findById(oi.getId()).orElse(null);
        assert oiToUpdate != null;
        if (!Objects.equals(oiToUpdate.getItem(), oi.getItem())) {
            oiToUpdate.getItem().getOrderItems().remove(oiToUpdate);
            oiToUpdate.setItem(oi.getItem());
        }
        if (!Objects.equals(oiToUpdate.getOrder(), oi.getOrder())) {
            oiToUpdate.getOrder().getOrderItems().remove(oiToUpdate);
            oiToUpdate.setOrder(oi.getOrder());
        }
        if (!Objects.equals(oiToUpdate.getQuantity(), oi.getQuantity()))
            oiToUpdate.setQuantity(oi.getQuantity());
        return oiToUpdate;
    }

    @Override
    public OrdersItems delete(Integer id) {
        if (oiRep.findById(id).isEmpty())
            return new OrdersItems();
        OrdersItems oi = oiRep.findById(id).orElse(null);
        if (oi == null)
            return new OrdersItems();
        Order orderToUpdate = orderRep.findById(oi.getOrder().getId()).orElse(null);
        Item itemToUpdate = itemRep.findById(oi.getOrder().getId()).orElse(null);
        if (orderToUpdate == null || itemToUpdate == null)
            return new OrdersItems();
        itemToUpdate.getOrderItems().remove(oi);
        orderToUpdate.getOrderItems().remove(oi);
        oiRep.deleteById(id);
        return oi;
    }
}
