package com.klozevitz.ordersdb.model.entities.order;

import com.klozevitz.ordersdb.model.entities.ordersItems.OrdersItems;
import com.klozevitz.ordersdb.model.entities.ordersItems.OrdersItemsDTO;

import java.util.HashSet;
import java.util.Set;

public class OrderDTO {
    private Integer id;
    private String description;
    private Integer clientId;
    private Set<OrdersItemsDTO> orderItems = new HashSet<>();
//    private Set<Integer> orderItemsId = new HashSet<>();

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

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Set<OrdersItemsDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrdersItemsDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.description = order.getDescription();
        this.clientId = order.getClient().getId();
        if (order.getOrderItems() != null)
            for (OrdersItems oi: order.getOrderItems())
                orderItems.add(new OrdersItemsDTO(oi));
    }

    public OrderDTO() {}

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", clientId=" + clientId +
                ", orderItems=" + orderItems +
                '}';
    }
}
