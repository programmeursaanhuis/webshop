package nl.hu.v2iac.webshopapp.model;

public class ServiceProvider {
	private static ProductService productService = new ProductService();
	private static CategorieService categorieService = new CategorieService();
	private static AanbiedingService aanbiedingService = new AanbiedingService();
	private static AdresService adresservice = new AdresService();
	
	public static ProductService getProductService(){
		return productService;
	}
	public static AanbiedingService getAanbiedingService() {
		return aanbiedingService;
	}
	public static CategorieService getCategorieService(){
		return categorieService;
	}
	public static AdresService getAdresService() {
		return adresservice;
	}
}
