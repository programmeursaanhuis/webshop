package nl.hu.v2iac.webshopapp.infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v2iac.webshopapp.model.Klant;

public class KlantDAO {

    private Connection connection;

    public KlantDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Klant> listAll() {
        List<Klant> result = new ArrayList<Klant>();

        try {

            PreparedStatement pstmt = connection.prepareStatement("SELECT klant_id, naam, afbeelding, woonadres FROM KLANT");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("klant_id");
                String naam = rs.getString("naam");
                String afbeelding = rs.getString("afbeelding");
                int woonadres = rs.getInt("woonadres"); //TODO Call AdresDAO instead

                result.add(new Klant(id, naam, afbeelding, woonadres));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Klant getById(int id) {
        Klant result = null;

        try {

            PreparedStatement pstmt = connection.prepareStatement("SELECT naam, afbeelding, woonadres FROM KLANT WHERE klant_id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String naam = rs.getString("naam");
                String afbeelding = rs.getString("afbeelding");
                int woonadres = rs.getInt("woonadres"); //TODO Call AdresDAO instead

                result = new Klant(id, naam, afbeelding, woonadres);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void create(Klant klant) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO KLANT(klant_id, naam, afbeelding, woonadres) VALUES(?,?,?,?)");
            pstmt.setInt(1, klant.getId());
            pstmt.setString(2, klant.getNaam());
            pstmt.setString(3, klant.getAfbeelding());
            pstmt.setInt(4, klant.getWoonadres());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Klant klant) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("UPDATE KLANT SET naam = ?, afbeelding = ?, woonadres = ? WHERE klant_id = ?");
            pstmt.setString(1, klant.getNaam());
            pstmt.setString(2, klant.getAfbeelding());
            pstmt.setInt(3, klant.getWoonadres());
            pstmt.setInt(4, klant.getId());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Klant klant) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM KLANT WHERE klant_id = ?");
            pstmt.setInt(1, klant.getId());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
