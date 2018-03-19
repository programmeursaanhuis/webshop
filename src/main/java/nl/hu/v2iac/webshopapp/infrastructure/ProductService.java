package nl.hu.v2iac.webshopapp.infrastructure;

import java.sql.SQLException;
import java.util.List;


import model.Product;

public class ProductService {
	private ProductDAO productDAO = new ProductDAO();
	
	public List<Product> listAll() {
		return productDAO.listAll();
	}
	
	public boolean create(Product product) {
		return productDAO.createProduct(product);
	}
	
	public boolean update(Product product) {
		return productDAO.updateProduct(product);
	}
	
	public boolean delete(Product product) {
		return productDAO.deleteProduct(product);
	}
}
