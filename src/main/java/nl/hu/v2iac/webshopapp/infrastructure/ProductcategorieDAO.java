package nl.hu.v2iac.webshopapp.infrastructure;

import model.Productcategorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductcategorieDAO {

    private Connection connection;

    public ProductcategorieDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Productcategorie> listAll() {
        List<Productcategorie> result = new ArrayList<Productcategorie>();

        try {

            PreparedStatement pstmt = connection.prepareStatement("SELECT productcat_id, categorie_id, product_id FROM PRODUCTCATEGORIE");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("productcat_id");
                int categorie = rs.getInt("categorie_id"); //TODO Call CategorieDAO instead
                int product = rs.getInt("product_id"); //TODO Call ProductDAO instead

                result.add(new Productcategorie(id, categorie, product));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Productcategorie findById(int id) {
        Productcategorie result = null;

        try {

            PreparedStatement pstmt = connection.prepareStatement("SELECT categorie_id, product_id FROM PRODUCTCATEGORIE WHERE productcat_id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int categorie = rs.getInt("categorie_id"); //TODO Call CategorieDAO instead
                int product = rs.getInt("product_id"); //TODO Call ProductDAO instead

                result = new Productcategorie(id, categorie, product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void create(Productcategorie productcategorie) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO PRODUCTCATEGORIE(productcat_id, categorie_id, product_id VALUES(?,?,?))");
            pstmt.setInt(1, productcategorie.getId());
            pstmt.setInt(2, productcategorie.getCategorie());
            pstmt.setInt(3, productcategorie.getProduct());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Productcategorie productcategorie) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("UPDATE PRODUCTCATEGORIE SET categorie_id = ?, product_id = ? WHERE productcat_id = ?");
            pstmt.setInt(1, productcategorie.getCategorie());
            pstmt.setInt(2, productcategorie.getProduct());
            pstmt.setInt(3, productcategorie.getId());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Productcategorie productcategorie) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM PRODUCTCATEGORIE WHERE productcat_id = ?");
            pstmt.setInt(1, productcategorie.getId());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
