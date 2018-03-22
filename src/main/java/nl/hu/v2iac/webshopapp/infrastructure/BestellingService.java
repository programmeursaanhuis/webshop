package nl.hu.v2iac.webshopapp.infrastructure;

import java.util.List;

import model.Bestelling;
import model.Klant;

public class BestellingService {
	private BestellingDAO bestellingDAO = new BestellingDAO(null);
	
	public List<Bestelling> listAll() {
		return bestellingDAO.listAll();
	}
	
	public Bestelling findKlant(int id) {
		return bestellingDAO.findById(id);
	}
	
	public boolean create(Bestelling bestelling) {
		return bestellingDAO.create(bestelling);
	}
	
	public boolean update(Bestelling bestelling) {
		return bestellingDAO.update(bestelling);
	}
	
	public boolean delete(Bestelling bestelling) {
		return bestellingDAO.delete(bestelling);
	}

}
