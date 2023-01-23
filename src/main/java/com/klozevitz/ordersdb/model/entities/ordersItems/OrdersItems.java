package com.klozevitz.ordersdb.model.entities.ordersItems;

import com.klozevitz.ordersdb.model.entities.item.Item;
import com.klozevitz.ordersdb.model.entities.order.Order;

import javax.persistence.*;

@Entity
@Table(name = "order_items_T")
public class OrdersItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public OrdersItems() {
        this.id = -1;
        this.item = new Item();
        this.quantity = -1;
        this.order = new Order();
    }

    public OrdersItems(Integer id, Integer quantity, Item item, Order order) {
        this.id = id;
        this.quantity = quantity;
        this.item = item;
        this.order = order;
    }

    public OrdersItems(Integer quantity, Item item, Order order) {
        this.quantity = quantity;
        this.item = item;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
