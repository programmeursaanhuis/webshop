package nl.hu.v2iac.webshopapp.service;

import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import nl.hu.v2iac.webshopapp.model.CategorieService;
import nl.hu.v2iac.webshopapp.model.ServiceProvider;
import nl.hu.v2iac.webshopapp.model.Categorie;


@Path("categorie")
public class CategorieResource {
    private CategorieService service = ServiceProvider.getCategorieService();
    
    @Path("/getcategories")
	@GET
	@Produces("application/json")
	public String findAll(@PathParam("id") int id) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Categorie c : service.listAll()) {
			JsonObjectBuilder job = productToJsonObject(c);
			jab.add(job);
		}
		return jab.build().toString();
	}
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String findCatById(@PathParam("id") int id){
    		JsonObjectBuilder job = Json.createObjectBuilder();
		Categorie c = service.findCategorie(id);
		if (c != null) {
			job.add("id", c.getId());
			job.add("categorie", c.getCategorie());
			
		}else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return job.build().toString();
    }    
    
    @POST
	@Path("/createcategory")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(String json) throws SQLException { 
    		// json komt aan als json dus je hebt geen FormParams nodig
		//System.out.println("Grrsrc: json "+json);
		JsonObject object = stringToJson(json);
		float myFloatValue = BigDecimal.valueOf(((JSONObject) object).getDouble("aDouble")).floatValue();
		
		Categorie categorie = 
			new Categorie
			(	object.getInt("id"),
				object.getString("categorie")
			);
		
		if ( service.create(categorie)== true){
			return Response.ok().build();
		} else {
			return Response.status(401).build();
		}
	}
	
	@DELETE
	@Path("/deletecategorie")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(String json) throws SQLException { 
		JsonObject object = stringToJson(json);
		float myFloatValue = BigDecimal.valueOf(((JSONObject) object).getDouble("aDouble")).floatValue();
		
		Categorie categorie = 
				new Categorie
				(	object.getInt("id"),
					object.getString("categorie")
				);
		
		return Response.ok().build();
	}
	
	private JsonObject stringToJson(String jsonString) {
		JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
		JsonObject object = jsonReader.readObject();
		jsonReader.close();
		return object;
	}

	private JsonObjectBuilder productToJsonObject(Categorie c){
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("id", c.getId()).add("categorie", c.getCategorie());
		return job;
	}
}