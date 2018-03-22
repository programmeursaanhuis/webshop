package nl.hu.v2iac.webshopapp.model;

import java.sql.SQLException;
import java.util.List;

import nl.hu.v2iac.webshopapp.infrastructure.ProductDAO;

public class ProductService {
	private ProductDAO productDAO = new ProductDAO();
	
	public List<Product> listAll() {
		return productDAO.listAll();
	}
	
	public Product findProduct(int id) {
		return productDAO.findById(id);
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
