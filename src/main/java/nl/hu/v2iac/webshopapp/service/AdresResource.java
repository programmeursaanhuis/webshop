package nl.hu.v2iac.webshopapp.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.hu.v2iac.webshopapp.infrastructure.AdresService;
import nl.hu.v2iac.webshopapp.infrastructure.ServiceProvider;

@Path("/adres/")
public class AdresResource {
    private AdresService service = ServiceProvider.getAdresService();
    
    @GET
    @Path("/{ID}")
    @Produces(MediaType.APPLICATION_JSON)
    public int adresById(@PathParam("id") int id){
    	return id;
    }    
}

