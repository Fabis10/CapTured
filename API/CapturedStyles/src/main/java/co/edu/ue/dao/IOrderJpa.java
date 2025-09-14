package co.edu.ue.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ue.model.Order;
import co.edu.ue.model.Order.Status;
import co.edu.ue.model.Orderdetail;



public interface IOrderJpa extends JpaRepository<Order, Integer>{
	
    List<Order> findByIdUser(int idUser);
    
    List<Order> findByOrdFechaPedido(Date ordFechaPedido);
    
    List<Order> findByOrderdetails(List<Orderdetail> orderdetails);
    
    List<Order> findByOrdStatus(Status status);
    
}
