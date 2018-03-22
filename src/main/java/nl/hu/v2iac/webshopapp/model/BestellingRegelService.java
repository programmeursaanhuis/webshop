package nl.hu.v2iac.webshopapp.model;

import java.util.List;

import nl.hu.v2iac.webshopapp.infrastructure.BestelregelDAO;

public class BestellingRegelService {
	private BestelregelDAO bestelregelDAO = new BestelregelDAO();
	
	public List<Bestelregel> listAll() {
		return bestelregelDAO.listAll();
	}
	
	public Bestelregel findById(int id) {
		return bestelregelDAO.findById(id);
	}
	
	public boolean create(Bestelregel bestelRegel) {
		return bestelregelDAO.create(bestelRegel);
	}
	
	public boolean update(Bestelregel bestelRegel) {
		return bestelregelDAO.update(bestelRegel);
	}
	
	public boolean delete(Bestelregel bestelRegel) {
		return bestelregelDAO.delete(bestelRegel);
	}

}
