package nl.hu.v2iac.webshopapp.infrastructure;

import model.Bestelregel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BestelregelDAO extends ConnectionBroker {

    private Connection connection;

    public List<Bestelregel> listAll() {
        List<Bestelregel> result = new ArrayList<Bestelregel>();

        try(Connection connection = super.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT bestellingRegel_id, aantal, prijs, product, bestelling FROM BESTELLING_REGEL");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("bestellingRegel_id");
                int aantal = rs.getInt("aantal");
                float prijs = rs.getFloat("prijs");
                int product = rs.getInt("product"); //TODO Call ProductDAO instead
                int bestelling = rs.getInt("bestelling"); //TODO Call BestellingDAO instead
                result.add(new Bestelregel(id, aantal, prijs, product, bestelling));
            }

            rs.close();
			pstmt.close();
			connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Bestelregel findById(int id) {
        Bestelregel result = null;

        try(Connection connection = super.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT aantal, prijs, product, bestelling FROM BESTELLING_REGEL WHERE bestellingRegel_id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int aantal = rs.getInt("aantal");
                float prijs = rs.getFloat("prijs");
                int product = rs.getInt("product"); //TODO Call ProductDAO instead
                int bestelling = rs.getInt("bestelling"); //TODO Call BestellingDAO instead

                result = new Bestelregel(id, aantal, prijs, product, bestelling);
            }
            
            rs.close();
			pstmt.close();
			connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean create(Bestelregel bestelregel) {
    		int affectedRows = 0;
    	
        try(Connection connection = super.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO BESTELLING_REGEL(bestellingRegel_id, aantal, prijs, product, bestelling) VALUES(?,?,?,?,?)");
            pstmt.setInt(1, bestelregel.getId());
            pstmt.setInt(2, bestelregel.getAantal());
            pstmt.setFloat(3, bestelregel.getPrijs());
            pstmt.setInt(4, bestelregel.getProduct());
            pstmt.setInt(5, bestelregel.getBestelling());
            pstmt.execute();
            
            connection.commit();
			pstmt.close();
			connection.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return affectedRows >= 1;
    }

    public boolean update(Bestelregel bestelregel) {
    		int affectedRows = 0;
        
    		try(Connection connection = super.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE BESTELLING_REGEL SET aantal = ?, prijs = ?, product = ?, bestelling = ? WHERE bestellingRegel_id = ?");
            pstmt.setInt(1, bestelregel.getAantal());
            pstmt.setFloat(2, bestelregel.getPrijs());
            pstmt.setInt(3, bestelregel.getProduct());
            pstmt.setInt(4, bestelregel.getBestelling());
            pstmt.setInt(5, bestelregel.getId());
            pstmt.execute();

            connection.commit();
			pstmt.close();
			connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    		
    		return affectedRows >= 1;
    }

    public boolean delete(Bestelregel bestelregel) {
    		int affectedRows = 0;
    		
        try(Connection connection = super.getConnection()) {

            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM BESTELLING_REGEL WHERE bestellingRegel_id = ?");
            pstmt.setInt(1, bestelregel.getId());
            pstmt.execute();

            connection.commit();
			pstmt.close();
			connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return affectedRows >= 1;
    }

}
