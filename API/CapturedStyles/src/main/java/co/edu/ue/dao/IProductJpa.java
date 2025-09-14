package co.edu.ue.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ue.model.Product;
import co.edu.ue.model.Product.Status;

public interface IProductJpa extends JpaRepository<Product, Integer>{
	
	List<Product> findByProdCategory (String category);
	
	List<Product> getByProdName(String prodName);

	List<Product> findByProdActive(Status prodActive);
	
	Product findByProdName (String name);
	
	boolean existsByProdName(String name);
}
