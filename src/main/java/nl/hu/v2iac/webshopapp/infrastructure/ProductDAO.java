package nl.hu.v2iac.webshopapp.infrastructure;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Product> listAll() {
        List<Product> result = new ArrayList<Product>();

        try {

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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Product findById(int id) {
        Product result = null;

        try {

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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void create (Product product) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO PRODUCT(product_id, naam, afbeelding, prijs, omschrijving, categorie) VALUES (?,?,?,?,?,?)");
            pstmt.setInt(1, product.getId());
            pstmt.setString(2, product.getNaam());
            pstmt.setString(3, product.getAfbeelding());
            pstmt.setFloat(4, product.getPrijs());
            pstmt.setString(5, product.getOmschrijving());
            pstmt.setInt(6, product.getCategorie());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update (Product product) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("UPDATE PRODUCT SET naam = ?, afbeelding = ?, prijs = ?, omschrijving = ?, categorie = ? WHERE product_id = ?");
            pstmt.setString(1, product.getNaam());
            pstmt.setString(2, product.getAfbeelding());
            pstmt.setFloat(3, product.getPrijs());
            pstmt.setString(4, product.getOmschrijving());
            pstmt.setInt(5, product.getCategorie());
            pstmt.setInt(6, product.getId());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete (Product product) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM PRODUCT WHERE product_id = ?");
            pstmt.setInt(1, product.getId());
            pstmt.execute();

            //TODO Get rid of references to Product in other tables

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
