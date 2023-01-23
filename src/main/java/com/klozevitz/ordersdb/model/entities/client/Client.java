package com.klozevitz.ordersdb.model.entities.client;

import com.klozevitz.ordersdb.model.entities.order.Order;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client_t")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false, length = 200)
    private String clientName;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Order> orders;

    public Client() {
        this.id = -1;
        this.clientName = "undefined";
        this.orders = new HashSet<>();
    }

    public Client(Integer id, String clientName) {
        this.id = id;
        this.clientName = clientName;
        this.orders = new HashSet<>();
    }

    public Client(String clientName) {
        this.clientName = clientName;
        this.orders = new HashSet<>();
    }

    public Client(Integer id, String clientName, Set<Order> orders) {
        this.id = id;
        this.clientName = clientName;
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", clientName='" + clientName + '\'' +
                ", orders=" + orders + '}';
    }
}
