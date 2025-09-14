package co.edu.ue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.model.Product;
import co.edu.ue.model.Product.Status;
import co.edu.ue.service.IProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "prd")
public class ProductController {
	
	@Autowired
	IProductService service;
	
		//Peticiones GET


	//Trae todos los productos
	@GetMapping("product")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> datos = service.allProducts();
		if(datos!=null) {			
			HttpHeaders headers = new HttpHeaders();
			headers.add("cantidad_datos", String.valueOf(datos.size()));
			return new ResponseEntity<List<Product>>(datos,headers,HttpStatus.OK);
		}return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
	}
	
	//Trae un solo producto por su ID
	@GetMapping(value = "product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("Id") int id){
		if(service.getById(id) ==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else return new ResponseEntity<Product>(service.getById(id),HttpStatus.ACCEPTED);
	}
	
	
	//Trae varios productos por su categoria
	@GetMapping(value = "product/category/{cat}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("cat") String category){
		List<Product> datos = service.getByCategory(category);
		if(datos !=null) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("cantidad_datos", String.valueOf(datos.size()));
			return new ResponseEntity<List<Product>>(service.getByCategory(category),HttpStatus.ACCEPTED);
		} else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//Trae un producto por su nombre
	@GetMapping(value = "product/name/{nm}")
	public ResponseEntity<Product> getProdByName(@PathVariable("nm") String name){
		Product dato = service.getByName(name);
		if(service.getByName(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else return new ResponseEntity<Product>(dato,HttpStatus.ACCEPTED);
	}
	
	
	//Trae todos los productos con un nombre
	@GetMapping(value = "product/name/all/{nm}" )
	public ResponseEntity<List<Product>> getProdsByName(@PathVariable("nm") String name){

		List<Product>datos = service.getAllByName(name);
		HttpHeaders headers = new HttpHeaders();
		headers.add("cantidad_datos", String.valueOf(datos.size()));
		 if(service.getAllByName(name) == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 }else return new ResponseEntity<List<Product>>(datos,HttpStatus.ACCEPTED);
	}
	 
	
	//Trae todos los productos por su status
	 @GetMapping(value = "product/status/{stus}")
	 public ResponseEntity<List<Product>> getProdsByStatus(@PathVariable("stus") Status status){
		 List<Product> product = service.getAllByStatus(status);
		 try {
			return new ResponseEntity<List<Product>>(product,HttpStatus.OK);
		} catch (Exception e) {			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		 
	 }
	
		//Peticiones Post
	
	 //Crea un producto
	@PostMapping(value="product", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> getMethodName(@RequestBody Product product) {
		if(!service.existsByName(product.getProdName())) {
			service.newProduct(product);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}else return new ResponseEntity<>(HttpStatus.FOUND);
	}
	
	
		//Peticiones Put
	
	//Actualiza datos del producto
	@PutMapping(value = "product/edit/{nm}")
	public ResponseEntity<Product> updateProduct(@PathVariable("nm") String name, @RequestBody Product newUpdatedProduct){
		if(service.existsByName(name)) {
			Product product = service.updateProduct(name, newUpdatedProduct);
			return new ResponseEntity<Product>(product,HttpStatus.CREATED);
		}return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	
	//Le cambia solo el status al producto("Dar de baja")	
	@PutMapping(value = "product/status/{name}")
	public ResponseEntity<Product> statusProduct(@PathVariable("status") String nm, @RequestBody Product newStatusProduct){
		if(service.existsByName(newStatusProduct.getProdName())) {
			Product product = service.vigentProduct(nm, newStatusProduct);
			return new ResponseEntity<Product>(product,HttpStatus.NO_CONTENT);
		}else return new ResponseEntity<Product>(HttpStatus.CONFLICT);
	}
	
		//Peticion Delete
	
	//Borra un usuario por su id
	@DeleteMapping(value = "product/delete/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("Id") int id){
		if(service.existById(id)) {
			service.deleteProduct(id);			
		} else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return ResponseEntity.noContent().build();
	}
	

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + ex.getMessage());
    }

}
