package co.edu.ue.service;

import co.edu.ue.model.Order;
import co.edu.ue.model.Order.Status;

import java.util.Date;
import java.util.List;

public interface IOrderService {

	Order createOrder(Order order);
	Order upOrder(Order order, int id);
	Order findById(int id);
    List<Order> allOrders();
    List<Order> allOrderByIdUser(int id);
    List<Order> allOrdersByDate(Date date);
    List<Order> allOrdersByStatus(Status status);
    void eraseOrderById(int id);
 }
