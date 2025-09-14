package co.edu.ue.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.model.Product;
import co.edu.ue.model.Product.Status;

@Repository
public class ProductDao implements IProductDao{
	
	@Autowired
	IProductJpa jpa;

	@Override
	public List<Product> searchByCategory(String category){
		return jpa.findByProdCategory(category);
	}
	
	@Override
	public List<Product> searchAllByName(String name) {
		return jpa.getByProdName(name);
	}
	@Override
	public Product searchById(int id){
		return jpa.findById(id).orElse(null);
	}

	@Override
	public Product addProduct(Product product) {
		return jpa.save(product);
	}

	@Override
	public Product searchByName(String name) {
		return jpa.findByProdName(name);
	}

	@Override
	public boolean existByName(String name) {
		return jpa.existsByProdName(name);
	}

	@Override
	public List<Product> searchAllByStatus(Status status) {
		return jpa.findByProdActive(status);
	}

	@Override
	public Product updateProduct(Product product) {
		return jpa.save(product);
	}

	@Override
	public void deleteProduct(int id) {
		jpa.deleteById(id);
	}

	@Override
	public List<Product> allProducts() {
		return jpa.findAll();
	}

	@Override
	public boolean existById(int id) {
		return jpa.existsById(id);
	}
}
