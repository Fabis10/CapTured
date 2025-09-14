package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.dao.IProductDao;
import co.edu.ue.model.Product;
import co.edu.ue.model.Product.Status;

@Service
public class ProductService implements IProductService{
	
	@Autowired
	IProductDao dao;
	
		//CREATE

	@Override
	public Product newProduct(Product product) {
		return dao.addProduct(product);
	}

		//READ
	
	@Override
	public List<Product> getByCategory(String category) {
		return dao.searchByCategory(category);
	}
	
	@Override
	public List<Product> getAllByName(String name) {
		return dao.searchAllByName(name);
	}

	@Override
	public Product getById(int id) {
		return dao.searchById(id);
	}


	@Override
	public Product getByName(String name) {
		return dao.searchByName(name);
	}

	@Override
	public List<Product> getAllByStatus(Status status) {
		return dao.searchAllByStatus(status);
	}
	
	@Override
	public List<Product> allProducts() {
		return dao.allProducts();
	}

	@Override
	public boolean existsByName(String name) {
		return dao.existByName(name);
	}
	
	@Override
	public boolean existById(int id) {
		return dao.existById(id);
	}

	
		//UPDATE
	
	@Override
	public Product updateProduct(String name, Product newProduct) {
		Product oldDatProduct = dao.searchByName(name);
		
		oldDatProduct.setProdCategory(newProduct.getProdCategory());
		oldDatProduct.setProdName(newProduct.getProdName());
		oldDatProduct.setProdPrecio(newProduct.getProdPrecio());
		oldDatProduct.setProdImg(newProduct.getProdImg());
		oldDatProduct.setProdIdHTML(newProduct.getProdIdHTML());
		
		
		return dao.updateProduct(oldDatProduct);
	}
	
	@Override
	public Product vigentProduct(String name, Product newStatus) {
		Product oldStatus = dao.searchByName(name);
		
		oldStatus.setProdActive(newStatus.getProdActive());
		
		return dao.updateProduct(newStatus);
	}
	
		//El delete

	@Override
	public void deleteProduct(int id) {
		dao.deleteProduct(id);
	}



	
}
