package com.klozevitz.ordersdb.model.dao.services.order;

import com.klozevitz.ordersdb.model.dao.IDaoDB;
import com.klozevitz.ordersdb.model.entities.order.Order;
import com.klozevitz.ordersdb.model.entities.order.OrderReportDTO;

import java.util.List;

public interface IDaoOrder extends IDaoDB<Order> {
    OrderReportDTO orderInfo(int id);
//    Map<String, Integer> check(int id);

    List<String> check(int id);
}
