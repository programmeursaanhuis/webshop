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
import nl.hu.v2iac.webshopapp.model.Klant;
import nl.hu.v2iac.webshopapp.model.ServiceProvider;
import nl.hu.v2iac.webshopapp.model.KlantService;

@Path("klant")
public class KlantResource {
    private KlantService service = ServiceProvider.getKlantService();
    
    @Path("/klanten")
	@GET
	@Produces("application/json")
	public String findAll(@PathParam("id") int id) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Klant k : service.listAll()) {
			JsonObjectBuilder job = productToJsonObject(k);
			jab.add(job);
		}
		return jab.build().toString();
	}
    
    @GET
    @Path("/{ID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String findKlantById(@PathParam("ID") int id){
    		JsonObjectBuilder job = Json.createObjectBuilder();
		Klant k = service.findKlant(id);
		if (k!=null) {
			job.add("id", k.getId());
			job.add("naam", k.getNaam());
			job.add("afbeelding", k.getAfbeelding());
			job.add("woonadres", k.getWoonadres());
		}else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return job.build().toString();
    }    
    
    @POST
	@Path("/createklant")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(String json) throws SQLException { 
		JsonObject object = stringToJson(json);
		Klant k = new Klant (object.getInt("id"), object.getString("naam"), object.getString("afbeelding"), object.getInt("woonadres"));
		
		if ( service.create(k)== true){
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

	private JsonObjectBuilder productToJsonObject(Klant k){
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("id", k.getId()).add("naam", k.getNaam()).add("afbeelding", k.getAfbeelding()).add("woonadres", k.getWoonadres());
		return job;
	}
}
