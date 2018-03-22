package nl.hu.v2iac.webshopapp.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.hu.v2iac.webshopapp.model.CategorieService;
import nl.hu.v2iac.webshopapp.model.ServiceProvider;

@Path("categorie")
public class CategorieResource {
    private CategorieService service = ServiceProvider.getCategorieService();
    
    @GET
    @Path("/{ID}")
    @Produces(MediaType.APPLICATION_JSON)
    public int categorieById(@PathParam("id") int id){
    	return id;
    }    
}