package com.klozevitz.ordersdb.model.entities.client;

import com.klozevitz.ordersdb.model.entities.order.Order;
import com.klozevitz.ordersdb.model.entities.order.OrderDTO;

import java.util.HashSet;
import java.util.Set;

public class ClientDTO {
    private Integer id;

    private String clientName;

    private Set<OrderDTO> orders = new HashSet<>();
//    private Set<Integer> ordersId = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Set<OrderDTO> getOrdersId() {
        return orders;
    }

    public void setOrdersId(Set<OrderDTO> ordersId) {
        this.orders = ordersId;
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.clientName = client.getClientName();
        if (client.getOrders() != null)
            for (Order order: client.getOrders())
                this.orders.add(new OrderDTO(order));
    }

    public ClientDTO() {}

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", ordersId=" + orders +
                '}';
    }
}
