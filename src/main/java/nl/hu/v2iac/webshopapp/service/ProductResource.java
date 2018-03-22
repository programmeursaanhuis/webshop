package nl.hu.v2iac.webshopapp.service;

import java.io.StringReader;
import java.sql.SQLException;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.math.BigDecimal;
import org.json.JSONObject;

import model.Product;
import nl.hu.v2iac.webshopapp.infrastructure.ProductService;
import nl.hu.v2iac.webshopapp.infrastructure.ServiceProvider;
import nl.hu.v2iac.webshopapp.infrastructure.ProductDAO;

@Path("product")
public class ProductResource {
	ProductService service = ServiceProvider.getProductService();
	ProductDAO productDAO = new ProductDAO();
	
	@Path("/getproducts")
	@GET
	@Produces("application/json")
	public String findAll(@PathParam("id") int id) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Product p : service.listAll()) {
			JsonObjectBuilder job = productToJsonObject(p);
			jab.add(job);
		}
		return jab.build().toString();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public String findById(@PathParam("id") int id) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		Product p=service.findProduct(id);
		if (p!=null) {
			job.add("id", p.getId());
			job.add("naam", p.getNaam());
			job.add("prijs", p.getPrijs());
			job.add("omschrijving", p.getOmschrijving());
			
		}else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return job.build().toString();
	}
	
	@POST
	@Path("/createproduct")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(String json) throws SQLException { // json komt aan als json dus je hebt geen FormParams nodig
		//System.out.println("Grrsrc: json "+json);
		JsonObject object = stringToJson(json);
		float myFloatValue = BigDecimal.valueOf(((JSONObject) object).getDouble("aDouble")).floatValue();
		
		Product product = 
			new Product
			(	object.getInt("id"),
				object.getString("naam"),
				object.getString("afbeelding"),
				myFloatValue,
				object.getString("omschrijving"),
				object.getInt("categorie")
				);
		
		if ( service.create(product)== true){
			return Response.ok().build();
		} else {
			return Response.status(401).build();
		}
	}
	
	@DELETE
	@Path("/deleteproduct")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(String json) throws SQLException { // json komt aan als json dus je hebt geen FormParams nodig
		//System.out.println("Grrsrc: json "+json);
		JsonObject object = stringToJson(json);
		float myFloatValue = BigDecimal.valueOf(((JSONObject) object).getDouble("aDouble")).floatValue();
		
		Product product = 
			new Product
			(	object.getInt("id"),
				object.getString("naam"),
				object.getString("afbeelding"),
				myFloatValue,
				object.getString("omschrijving"),
				object.getInt("categorie"));
		
		return Response.ok().build();
	}
	
	private JsonObject stringToJson(String jsonString) {
		JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
		JsonObject object = jsonReader.readObject();
		jsonReader.close();
		return object;
	}

	private JsonObjectBuilder productToJsonObject(Product p){
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("id", p.getId())
		.add("naam", p.getNaam())
		.add("afbeelding", p.getAfbeelding())
		.add("prijs", p.getPrijs())
		.add("omschrijving", p.getOmschrijving())
		.add("categorie", p.getCategorie()); 
		return job;
	}
	
	
}