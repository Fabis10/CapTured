package co.edu.ue.dao;

import co.edu.ue.model.Order;
import co.edu.ue.model.Order.Status;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao implements IOrderDao {

	@Autowired
    IOrderJpa jpa;

    @Override
    public List<Order> getAllOrders() {
        return jpa.findAll();
    }

    @Override
    public Order getOrderById(int id) {
    	return jpa.findById(id).orElseThrow(null);
    }
    
    @Override
    public List<Order> getAllOrderByIdUser(int id) {
        return jpa.findByIdUser(id);
    }

    @Override
    public Order addOrder(Order order) {
        return jpa.save(order);
    }

    @Override
    public List<Order> getAllByDate(Date date) {
        return jpa.findByOrdFechaPedido(date);
    }

	@Override
	public List<Order> getAllByStatus(Status status) {
		return jpa.findByOrdStatus(status);
	}

	@Override
	public Order editOrder(Order order, int id) {
		return jpa.save(order);
	}

	@Override
	public void deleteOrder(int id) {
		jpa.deleteById(id);
	}

}
