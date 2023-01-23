package com.klozevitz.ordersdb.model.dao.services.item;

import com.klozevitz.ordersdb.model.entities.item.Item;
import com.klozevitz.ordersdb.model.entities.ordersItems.OrdersItems;
import com.klozevitz.ordersdb.model.entities.ordersItems.OrdersItemsDTO;
import com.klozevitz.ordersdb.model.repositories.IItemRepository;
import com.klozevitz.ordersdb.model.repositories.IOrderRepository;
import com.klozevitz.ordersdb.model.repositories.IOrdersItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService implements IDaoItem {
    @Autowired
    private IItemRepository itemRep;

    @Autowired
    private IOrderRepository orderRep;

    @Autowired
    private IOrdersItemsRepository oiRep;

    @Override
    public List<Item> findAll() {
        return (List<Item>) itemRep.findAll();
    }

    @Override
    public Item findById(Integer id) {
        return itemRep.findById(id).orElse(new Item());
    }

    @Override
    public Item save(Item item) {
        return itemRep.save(item);
    }

    @Override
    @Transactional
    public Item update(Item item) {
        if (itemRep.findById(item.getId()).isEmpty())
            return new Item();
        return updateItem(item);
    }

    private Item updateItem(Item item) {
        Item itemToUpdate = itemRep.findById(item.getId()).orElse(null);
        assert itemToUpdate != null;
        if (item.getItemName() != null)
            itemToUpdate.setItemName(item.getItemName());
        if (item.getItemArticle() != null)
            itemToUpdate.setItemArticle(item.getItemArticle());
        if (item.getPrice() != null)
            itemToUpdate.setPrice(item.getPrice());
        return itemToUpdate;
    }

    @Override
    public Item delete(Integer id) {
        Item item = itemRep.findById(id).orElse(null);
        if (item == null)
            return new Item();
        deleteItemFromOrders(item);
        itemRep.deleteById(id);
        return item;
    }

    private void deleteItemFromOrders(Item item) {
        for (OrdersItems oi: oiRep.findAllByItem(item)) {
            orderRep.findById(oi.getOrder().getId()).get().getOrderItems().remove(oi);
            oiRep.delete(oi);
        }
    }
}
