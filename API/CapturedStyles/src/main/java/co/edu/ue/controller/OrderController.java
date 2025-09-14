package co.edu.ue.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.model.Order;
import co.edu.ue.service.IOrderService;

@RestController
@CrossOrigin(origins= "*")
@RequestMapping(value = "ord")
public class OrderController {
	
	@Autowired
	IOrderService service;
	
		//GET
	
	@GetMapping(value ="ordenes")
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> order = service.allOrders();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cantidad de datos", String.valueOf(order.size()));
		return new ResponseEntity<List<Order>>(order, headers,HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value="ordenes/{id}")
	public ResponseEntity<Order> getAllOrderById(@PathVariable("id") int ID){
		Order order = service.findById(ID);
		return new ResponseEntity<Order>(order,HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "ordenes/user/{id}")
	public ResponseEntity<List<Order>> getAllOrdersByIdUser(@PathVariable("id") int Id){
		List<Order> order = service.allOrderByIdUser(Id);
		return new ResponseEntity<List<Order>>(order,HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "ordenes/fecha/{date}")
	public ResponseEntity<List<Order>> getAllOrdersByDate(@PathVariable("date") Date Date){
		List<Order> order = service.allOrdersByDate(Date);
		return new ResponseEntity<List<Order>>(order,HttpStatus.ACCEPTED);
	}
	
	
		//POST
	
	
	@PostMapping(value = "/ordenes/nuevo")
	public ResponseEntity<Order> newOrder(@RequestBody Order order) {
		try {
	        Order createdOrder = service.createOrder(order);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body(null);
	    }
	}
	
		//PUT
	
		//DELETE

	

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + ex.getMessage());
    }
}
