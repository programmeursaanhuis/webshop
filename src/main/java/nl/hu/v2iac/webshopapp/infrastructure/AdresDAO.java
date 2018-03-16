package nl.hu.v2iac.webshopapp.infrastructure;

import model.Adres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdresDAO {

    private Connection connection;

    public AdresDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Adres> listAll() {
        List<Adres> result = new ArrayList<Adres>();

        try {

            PreparedStatement pstmt = connection.prepareStatement("SELECT adres_id, straat, straatnummer FROM ADRES");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("adres_id");
                String straat = rs.getString("straat");
                int straatnummer = rs.getInt("straatnummer");

                result.add(new Adres(id, straat, straatnummer));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Adres findById(int id) {
        Adres result = null;

        try {

            PreparedStatement pstmt = connection.prepareStatement("SELECT straat, straatnummer FROM ADRES WHERE adres_id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String straat = rs.getString("straat");
                int straatnummer = rs.getInt("straatnummer");

                result = new Adres(id, straat, straatnummer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void create (Adres adres) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO ADRES(adres_id, straat, straatnummer) VALUES (?,?,?)");
            pstmt.setInt(1, adres.getId());
            pstmt.setString(2, adres.getStraat());
            pstmt.setInt(3, adres.getStraatnummer());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update (Adres adres) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("UPDATE ADRES SET straat = ?, straatnummer = ? WHERE adres_id = ?");
            pstmt.setString(1, adres.getStraat());
            pstmt.setInt(2, adres.getStraatnummer());
            pstmt.setInt(3, adres.getId());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete (Adres adres) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM ADRES WHERE adres_id = ?");
            pstmt.setInt(1, adres.getId());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
