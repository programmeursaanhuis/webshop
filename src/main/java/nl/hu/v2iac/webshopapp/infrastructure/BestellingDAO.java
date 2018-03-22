package nl.hu.v2iac.webshopapp.infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v2iac.webshopapp.model.Bestelling;

public class BestellingDAO {

    private Connection connection;

    public BestellingDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Bestelling> listAll() {
        List<Bestelling> result = new ArrayList<Bestelling>();

        try {

            PreparedStatement pstmt = connection.prepareStatement("SELECT bestelling_id, afleverAdres FROM BESTELLING");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("bestelling_id");
                int afleverAdres = rs.getInt("afleverAdres"); //TODO Call AdresDAO instead

                result.add(new Bestelling(id, afleverAdres));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Bestelling findById(int id) {
        Bestelling result = null;

        try {

            PreparedStatement pstmt = connection.prepareStatement("SELECT afleverAdres FROM BESTELLING WHERE bestelling_id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int afleverAdres = rs.getInt("afleverAdres"); //TODO Call AdresDAO instead

                result = new Bestelling(id, afleverAdres);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void create(Bestelling bestelling) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO BESTELLING(bestelling_id, afleverAdres) VALUES (?,?)");
            pstmt.setInt(1, bestelling.getId());
            pstmt.setInt(2, bestelling.getAfleverAdres());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Bestelling bestelling) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("UPDATE BESTELLING SET afleverAdres = ? WHERE bestelling_id = ?");
            pstmt.setInt(1, bestelling.getAfleverAdres());
            pstmt.setInt(2, bestelling.getId());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Bestelling bestelling) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM BESTELLING WHERE bestelling_id = ?");
            pstmt.setInt(1, bestelling.getId());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
