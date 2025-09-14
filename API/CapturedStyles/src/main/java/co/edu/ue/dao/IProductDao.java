package co.edu.ue.dao;

import java.util.List;

import co.edu.ue.model.Product;
import co.edu.ue.model.Product.Status;

public interface IProductDao {
	
	Product addProduct(Product product);
	Product updateProduct(Product product);
	Product searchById(int id);
	Product searchByName(String name);
	List<Product> allProducts();
	List<Product> searchByCategory(String category);
	List<Product> searchAllByStatus(Status status);
	List<Product> searchAllByName(String name);
	boolean existByName(String name);
	boolean existById(int id);
	void deleteProduct(int id);
}
