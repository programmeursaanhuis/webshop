package nl.hu.v2iac.webshopapp.infrastructure;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends ConnectionBroker{

    public List<Product> listAll() {
        List<Product> result = new ArrayList<Product>();

        try (Connection connection = super.getConnection()) {

            PreparedStatement pstmt = connection.prepareStatement("SELECT product_id, naam, afbeelding, prijs, omschrijving, categorie FROM PRODUCT");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("product_id");
                String naam = rs.getString("naam");
                String afbeelding = rs.getString("afbeelding");
                float prijs = rs.getFloat("prijs");
                String omschrijving = rs.getString("omschrijving");

                //TODO Call CategorieDAO instead
                int categorie = rs.getInt("categorie");

                result.add(new Product(id, naam, afbeelding, prijs, omschrijving, categorie));
            }
            
            rs.close();
    			pstmt.close();
    			connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Product findById(int id) {
        Product result = null;

        try (Connection connection = super.getConnection()) {

            PreparedStatement pstmt = connection.prepareStatement("SELECT naam, afbeelding, prijs, omschrijving, categorie FROM PRODUCT WHERE product_id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String naam = rs.getString("naam");
                String afbeelding = rs.getString("afbeelding");
                float prijs = rs.getFloat("prijs");
                String omschrijving = rs.getString("omschrijving");

                //TODO Call CategorieDAO instead
                int categorie = rs.getInt("categorie");

                result = new Product(id, naam, afbeelding, prijs, omschrijving, categorie);
            }
            rs.close();
    		pstmt.close();
    		connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private boolean create (Product product) {
    	int affectedRows = 0;
    	try (Connection connection = super.getConnection()) {

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO PRODUCT(product_id, naam, afbeelding, prijs, omschrijving, categorie) VALUES (?,?,?,?,?,?)");
            pstmt.setInt(1, product.getId());
            pstmt.setString(2, product.getNaam());
            pstmt.setString(3, product.getAfbeelding());
            pstmt.setFloat(4, product.getPrijs());
            pstmt.setString(5, product.getOmschrijving());
            pstmt.setInt(6, product.getCategorie());
            pstmt.execute();
    			
            connection.commit();
            pstmt.close();
    			connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return affectedRows >= 1;
    }

    private boolean update (Product product) {
	    	int affectedRows = 0;
			
	    	try (Connection connection = super.getConnection()) {
	
            PreparedStatement pstmt = connection.prepareStatement("UPDATE PRODUCT SET naam = ?, afbeelding = ?, prijs = ?, omschrijving = ?, categorie = ? WHERE product_id = ?");
            pstmt.setString(1, product.getNaam());
            pstmt.setString(2, product.getAfbeelding());
            pstmt.setFloat(3, product.getPrijs());
            pstmt.setString(4, product.getOmschrijving());
            pstmt.setInt(5, product.getCategorie());
            pstmt.setInt(6, product.getId());
            pstmt.execute();
    		
            connection.commit();
            pstmt.close();
    			connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	    		
	    	return affectedRows >= 1;
    }

    private boolean delete (Product product) {
	    	int affectedRows = 0;
			
	    	try (Connection connection = super.getConnection()) {
	
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM PRODUCT WHERE product_id = ?");
            pstmt.setInt(1, product.getId());
            pstmt.execute();
    			
            connection.commit();
            pstmt.close();
    			connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	    	return affectedRows >= 1;
    }
    
    public boolean createProduct(Product product) {
    	return create(product);
    }
    
    public boolean updateProduct(Product product) {
    	return update(product);
    }
    
    public boolean deleteProduct(Product product) {
    	return delete(product);
    }
}
