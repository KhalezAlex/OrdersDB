package com.klozevitz.ordersdb.model.entities.order;

import com.klozevitz.ordersdb.model.entities.client.Client;
import com.klozevitz.ordersdb.model.entities.client.ClientDTO;
import com.klozevitz.ordersdb.model.entities.item.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class OrderReportDTO {
    private int id;
    private String description;
    private ClientDTO client;
    private Map<String, Integer> items = new HashMap<>();
//    private Map<ItemDTO, Integer> items = new HashMap<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public OrderReportDTO(Order order) {
        this.id = order.getId();
        this.description = order.getDescription();
        this.client = new ClientDTO(order.getClient());
    }

    public OrderReportDTO() {
        this.id = -1;
        this.description = "undefined";
        this.client = new ClientDTO();
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", description='" + description + '\'' +
                ", client=" + client +
                ", items=" + items +
                '}';
    }
}
