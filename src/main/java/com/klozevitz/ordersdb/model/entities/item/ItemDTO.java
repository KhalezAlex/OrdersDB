package com.klozevitz.ordersdb.model.entities.item;

import com.klozevitz.ordersdb.model.entities.ordersItems.OrdersItems;
import com.klozevitz.ordersdb.model.entities.ordersItems.OrdersItemsDTO;

import java.util.HashSet;
import java.util.Set;

public class ItemDTO {
    private Integer id;
    private String itemName;
    private Long itemArticle;
    private int price;
    private Set<OrdersItemsDTO> orderItems = new HashSet<>();
//    private Set<Integer> orderItemsId = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemArticle() {
        return itemArticle;
    }

    public void setItemArticle(Long itemArticle) {
        this.itemArticle = itemArticle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Set<OrdersItemsDTO> getOrderItemsId() {
        return orderItems;
    }

    public void setOrderItemsId(Set<OrdersItemsDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public ItemDTO() {
    }

    public ItemDTO (Item item) {
        this.id = item.getId();
        this.itemArticle = item.getItemArticle();
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        if (item.getOrderItems() != null)
            for (OrdersItems oi: item.getOrderItems())
                this.orderItems.add(new OrdersItemsDTO(oi));
    }
}
