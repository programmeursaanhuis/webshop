package nl.hu.v2iac.webshopapp.infrastructure;

import model.Aanbieding;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AanbiedingDAO {

    private Connection connection;

    public AanbiedingDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Aanbieding> listAll() {
        List<Aanbieding> result = new ArrayList<Aanbieding>();

        try {

            PreparedStatement pstmt = connection.prepareStatement("SELECT aanbieding_id, vanDatum, totDatum, product, prijs, reclametekst FROM AANBIEDING");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("aanbieding_id");
                LocalDate vanDatum = rs.getDate("vanDatum").toLocalDate();
                LocalDate totDatum = rs.getDate("totDatum").toLocalDate();
                int product = rs.getInt("product"); //TODO Call ProductDAO instead
                float prijs = rs.getFloat("prijs");
                String reclametekst = rs.getString("reclametekst");

                result.add(new Aanbieding(id, vanDatum, totDatum, product, prijs, reclametekst));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Aanbieding findById(int id) {
        Aanbieding result = null;

        try {

            PreparedStatement pstmt = connection.prepareStatement("SELECT vanDatum, totDatum, product, prijs, reclametekst FROM AANBIEDING WHERE aanbieding_id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LocalDate vanDatum = rs.getDate("vanDatum").toLocalDate();
                LocalDate totDatum = rs.getDate("totDatum").toLocalDate();
                int product = rs.getInt("product"); //TODO Call ProductDAO instead
                float prijs = rs.getFloat("prijs");
                String reclametekst = rs.getString("reclametekst");

                result = new Aanbieding(id, vanDatum, totDatum, product, prijs, reclametekst);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void create (Aanbieding aanbieding) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO AANBIEDING(aanbieding_id, vanDatum, totDatum, product, prijs, reclametekst) VALUES (?,?,?,?,?,?)");
            pstmt.setInt(1, aanbieding.getId());
            pstmt.setDate(2, Date.valueOf(aanbieding.getVanDatum()));
            pstmt.setDate(3, Date.valueOf(aanbieding.getTotDatum()));
            pstmt.setInt(4, aanbieding.getProduct());
            pstmt.setFloat(5, aanbieding.getPrijs());
            pstmt.setString(6, aanbieding.getReclametekst());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update (Aanbieding aanbieding) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("UPDATE AANBIEDING SET vanDatum = ?, totDatum = ?, product = ?, prijs = ?, reclametekst = ? WHERE aambieding_id = ?");
            pstmt.setDate(1, Date.valueOf(aanbieding.getVanDatum()));
            pstmt.setDate(2, Date.valueOf(aanbieding.getTotDatum()));
            pstmt.setInt(3, aanbieding.getProduct());
            pstmt.setFloat(4, aanbieding.getPrijs());
            pstmt.setString(5, aanbieding.getReclametekst());
            pstmt.setInt(6, aanbieding.getId());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete (Aanbieding aanbieding) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM AANBIEDING WHERE aanbieding_id = ?");
            pstmt.setInt(1, aanbieding.getId());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
