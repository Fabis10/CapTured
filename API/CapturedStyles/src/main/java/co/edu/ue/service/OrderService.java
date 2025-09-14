package co.edu.ue.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.dao.IOrderDao;
import co.edu.ue.model.Order;
import co.edu.ue.model.Order.Status;

@Service
public class OrderService implements IOrderService {
	
	@Autowired
	IOrderDao dao;
	
	@Autowired
	IOrderValidator orderValidator;
	
	
		//Create
	
	@Override
	public Order createOrder(Order order) {
		
		if(!orderValidator.validateOrder(order)) {			
			return dao.addOrder(order); // Guarda la orden en la base de datos
		}else return dao.addOrder(order);
	}
		//READ
	
	@Override
	public List<Order> allOrders() {
		return dao.getAllOrders();
	}

	@Override
	public List<Order> allOrderByIdUser(int id) {
		return dao.getAllOrderByIdUser(id);
	}

	@Override
	public List<Order> allOrdersByDate(Date date) {
		return dao.getAllByDate(date);
	}

	@Override
	public List<Order> allOrdersByStatus(Status status) {
		return dao.getAllByStatus(status);
	}
	
	@Override
	public Order findById(int id) {
		return dao.getOrderById(id);
	}

		//Update
	
	@Override
	public Order upOrder(Order order, int id) {
		Order oldDataOrder = dao.getOrderById(id);
		
		oldDataOrder.setOrdStatus(order.getOrdStatus());
		return dao.editOrder(oldDataOrder, id);
	}

	
		//Delete
	@Override
	public void eraseOrderById(int id) {
		dao.deleteOrder(id);
	}
}
