package nl.hu.v2iac.webshopapp.service;

import java.io.StringReader;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.hu.v2iac.webshopapp.model.BestellingRegelService;
import nl.hu.v2iac.webshopapp.model.Bestelregel;
import nl.hu.v2iac.webshopapp.model.ServiceProvider;

@Path("bestellingregel")
public class BestellingRegelResource {
    private BestellingRegelService service = ServiceProvider.getBestellingRegelService();
    
    @Path("/getbestellingregels")
	@GET
	@Produces("application/json")
	public String findAll(@PathParam("id") int id) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Bestelregel br : service.listAll()) {
			JsonObjectBuilder job = productToJsonObject(br);
			jab.add(job);
		}
		return jab.build().toString();
	}
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String findCatById(@PathParam("id") int id){
    		JsonObjectBuilder job = Json.createObjectBuilder();
		Bestelregel br = service.findById(id);
		if (br != null) {			
			job.add("id", br.getId());
			job.add("aantal", br.getAantal());
			job.add("prijs", br.getPrijs());
			job.add("product", br.getProduct());
			job.add("bestelling", br.getBestelling());
		}else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return job.build().toString();
    }    
    
    @POST
	@Path("/createbestellingregel")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(String json) throws SQLException { 
		JsonObject object = stringToJson(json);
		
		Bestelregel br = new Bestelregel(
			object.getInt("id"),
			object.getInt("aantal"),
			object.getInt("prijs"),
			object.getInt("product"),
			object.getInt("bestelling")
		);
		
		if ( service.create(br)== true){
			return Response.ok().build();
		} else {
			return Response.status(401).build();
		}
	}
	
	private JsonObject stringToJson(String jsonString) {
		JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
		JsonObject object = jsonReader.readObject();
		jsonReader.close();
		return object;
	}

	private JsonObjectBuilder productToJsonObject(Bestelregel br){
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("id", br.getId()).add("aantal", br.getAantal()).add("prijs", br.getPrijs()).add("product", br.getProduct()).add("bestelling", br.getBestelling());
		return job;
	}
}