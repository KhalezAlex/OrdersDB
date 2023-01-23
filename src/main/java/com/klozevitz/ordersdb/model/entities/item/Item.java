package com.klozevitz.ordersdb.model.entities.item;

import com.klozevitz.ordersdb.model.entities.ordersItems.OrdersItems;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_t")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 200)
    private String itemName;
    @Column(nullable = false)
    private Long itemArticle;
    @Column(nullable = false)
    private Integer price;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<OrdersItems> orderItems;

    public Item() {
        this.id = -1;
        this.itemName = "undefined";
        this.itemArticle = -1L;
        this.price = -1;
        this.orderItems = new HashSet<>();
    }

    public Item(String itemName, Long itemArticle, int price) {
        this.itemName = itemName;
        this.itemArticle = itemArticle;
        this.price = price;
        this.orderItems = new HashSet<>();
    }

    public Item(Integer id, String itemName, Long itemArticle, int price) {
        this.id = id;
        this.itemName = itemName;
        this.itemArticle = itemArticle;
        this.price = price;
        this.orderItems = new HashSet<>();
    }

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<OrdersItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrdersItems> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemArticle=" + itemArticle +
                '}';
    }
}

