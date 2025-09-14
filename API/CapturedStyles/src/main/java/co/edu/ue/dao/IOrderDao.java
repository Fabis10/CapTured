package co.edu.ue.dao;

import co.edu.ue.model.Order;
import co.edu.ue.model.Order.Status;

import java.util.Date;
import java.util.List;

public interface IOrderDao {
    List<Order> getAllOrders();
    List<Order> getAllOrderByIdUser(int idUser);
    List<Order> getAllByDate(Date date);
    List<Order> getAllByStatus(Status status);
    Order getOrderById(int id);
    Order addOrder(Order order);
    Order editOrder(Order order, int id);
    void deleteOrder(int id);
}
