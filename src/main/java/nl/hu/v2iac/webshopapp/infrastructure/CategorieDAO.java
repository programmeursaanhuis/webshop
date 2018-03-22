package nl.hu.v2iac.webshopapp.infrastructure;

import model.Categorie;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAO {

    private Connection connection;

    public CategorieDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Categorie> listAll() {
        List<Categorie> result = new ArrayList<Categorie>();

        try {

            PreparedStatement pstmt = connection.prepareStatement("SELECT categorie_id, categorie FROM CATEGORIE");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("categorie_id");
                String categorie = rs.getString("categorie");

                result.add(new Categorie(id, categorie));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Categorie findById(int id) {
        Categorie result = null;

        try {

            PreparedStatement pstmt = connection.prepareStatement("SELECT categorie FROM CATEGORIE WHERE categorie_id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String categorie = rs.getString("categorie");

                result = new Categorie(id, categorie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean create(Categorie categorie) {
    		int affectedRows = 0;
    	
        try {

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO CATEGORIE(categorie_id, categorie) VALUES(?,?)");
            pstmt.setInt(1, categorie.getId());
            pstmt.setString(2, categorie.getCategorie());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    		return affectedRows >= 1;
    }

    public boolean update(Categorie categorie) {
    		int affectedRows = 0;
    		
        try {

            PreparedStatement pstmt = connection.prepareStatement("UPDATE CATEGORIE SET categorie = ? WHERE categorie_id = ?");
            pstmt.setString(1, categorie.getCategorie());
            pstmt.setInt(2, categorie.getId());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return affectedRows >= 1;
    }

    public boolean delete(Categorie categorie) {
    	int affectedRows = 0;
    	
        try {

            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM CATEGORIE WHERE categorie_id = ?");
            pstmt.setInt(1, categorie.getId());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return affectedRows >= 1;
    }
    
    public boolean createCategorie(Categorie categorie) {
    		return create(categorie);
    }
    
    public boolean updateCategorie(Categorie categorie) {
    		return update(categorie);
    }
    
    public boolean deleteCategorie(Categorie categorie) {
    		return delete(categorie);
    }

}
