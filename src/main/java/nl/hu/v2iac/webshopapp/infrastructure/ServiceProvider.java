package nl.hu.v2iac.webshopapp.infrastructure;

public class ServiceProvider {
	private static ProductService productService = new ProductService();
	
	public static ProductService getProductService(){
		return productService;
	}
	
}
