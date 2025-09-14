package co.edu.ue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.ue.model.Order;
import co.edu.ue.model.Orderdetail;

@Component
public class OrderValidator implements IOrderValidator{
	
	@Autowired
    private IProductService service;
	


	@Override
	public boolean validateOrder(Order order) {
		if(order.getOrdId() <=0) return false;
		
		if(order.getOrdPrecio() >=0 ) return false;
		
		if(order.getOrderdetails().isEmpty()) return false;
		
		for(Orderdetail detail : order.getOrderdetails()) {			
			if(!service.existById(detail.getProduct().getIdProducto())) return false;
		}
		
		
		return true;
	}
	
}

