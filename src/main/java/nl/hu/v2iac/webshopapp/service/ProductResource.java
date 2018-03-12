package nl.hu.v2iac.webshopapp.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.hu.v2iac.webshopapp.infrastructure.ProductService;
import nl.hu.v2iac.webshopapp.infrastructure.ServiceProvider;

public class ProductResource {
	ProductService service = ServiceProvider.getProductService();
	//TODO: nog niet af. code is nog in bouw
	@Path("getproduct")
	@GET
	@Produces("application/json")
	public int ProductByID(@PathParam("id") int id) {
		return id;
		
	}
}
