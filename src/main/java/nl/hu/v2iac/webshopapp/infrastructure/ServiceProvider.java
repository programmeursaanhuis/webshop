package nl.hu.v2iac.webshopapp.infrastructure;

public class ServiceProvider {
	private static ProductService productService = new ProductService();
	private static CategorieService categorieService = new CategorieService();
	private static KlantService klantService = new KlantService();
	
	public static ProductService getProductService(){
		return productService;
	}

	public static CategorieService getCategorieService(){
		return categorieService;
	}
	
	public static KlantService getKlantService() {
		return klantService;
	}

}
