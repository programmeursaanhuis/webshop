package nl.hu.v2iac.webshopapp.model;

import java.util.List;
import nl.hu.v2iac.webshopapp.infrastructure.CategorieDAO;

public class CategorieService {
	private CategorieDAO categorieDAO = new CategorieDAO();
	
	public List<Categorie> listAll() {
		return categorieDAO.listAll();
	}
	
	public Categorie findCategorie(int id) {
		return categorieDAO.findById(id);
	}
	
	public boolean create(Categorie categorie) {
		return categorieDAO.create(categorie);
	}
	
	public boolean update(Categorie categorie) {
		return categorieDAO.update(categorie);
	}
	
	public boolean delete(Categorie categorie) {
		return categorieDAO.delete(categorie);
	}
}