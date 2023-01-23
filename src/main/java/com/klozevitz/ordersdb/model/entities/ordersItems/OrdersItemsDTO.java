package com.klozevitz.ordersdb.model.entities.ordersItems;


public class OrdersItemsDTO {
    private Integer id;
    private Integer quantity;
    private Integer itemId;
    private Integer orderId;

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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public OrdersItemsDTO() {
    }

    public OrdersItemsDTO (OrdersItems oi) {
        this.id = oi.getId();
        this.quantity = oi.getQuantity();
        this.itemId = oi.getItem().getId();
        this.orderId = oi.getOrder().getId();
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", itemId=" + itemId +
                ", orderId=" + orderId +
                '}';
    }
}
