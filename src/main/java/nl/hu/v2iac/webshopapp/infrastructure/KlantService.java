package nl.hu.v2iac.webshopapp.infrastructure;

import java.util.List;
import nl.hu.v2iac.webshopapp.model.Klant;

public class KlantService {
	private KlantDAO klantDAO = new KlantDAO();
	
	public List<Klant> listAll() {
		return klantDAO.listAll();
	}
	
	public Klant findKlant(int id) {
		return klantDAO.getById(id);
	}
	
	public boolean create(Klant klant) {
		return klantDAO.create(klant);
	}
	
	public boolean update(Klant klant) {
		return klantDAO.update(klant);
	}
	
	public boolean delete(Klant klant) {
		return klantDAO.delete(klant);
	}
}
