package nl.hu.v2iac.webshopapp.infrastructure;

public class ServiceProvider {
	private static ProductService productService = new ProductService();
	private static AdresService adresService = new AdresService();
	private static CategorieService categorieService = new CategorieService();
	
	public static ProductService getProductService(){
		return productService;
	}
	
	public static AdresService getAdresService() {
		return adresService;
	}
	
	public static CategorieService getCategorieService() {
		return categorieService;
	}
}
