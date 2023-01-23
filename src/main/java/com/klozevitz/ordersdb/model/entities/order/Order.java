package com.klozevitz.ordersdb.model.entities.order;

import com.klozevitz.ordersdb.model.entities.ordersItems.OrdersItems;
import com.klozevitz.ordersdb.model.entities.client.Client;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_t")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrdersItems> orderItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<OrdersItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrdersItems> orderItems) {
        this.orderItems = orderItems;
    }

    public Order() {
        this.id = -1;
        this.description = "undefined";
        this.client = new Client();
        this.orderItems = new HashSet<>();
    }

    public Order(Integer id, String description, Client client) {
        this.id = id;
        this.description = description;
        this.client = client;
        this.orderItems = new HashSet<>();
    }

    public Order(String description, Client client) {
        this.description = description;
        this.client = client;
        this.orderItems = new HashSet<>();
    }

    public Order(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", description='" + description + '\'' +
                ", client=" + client + '}';
    }
}
