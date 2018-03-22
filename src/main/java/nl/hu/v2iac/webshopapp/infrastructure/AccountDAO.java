package nl.hu.v2iac.webshopapp.infrastructure;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v2iac.webshopapp.model.Account;

public class AccountDAO {

    private Connection connection;

    public AccountDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Account> listAll() {
        List<Account> result = new ArrayList<Account>();

        try {

            PreparedStatement pstmt = connection.prepareStatement("SELECT account_id, openDatum, isActief, factuurAdres, klant FROM ACCOUNT");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("account_id");
                LocalDate openDatum = rs.getDate("openDatum").toLocalDate();
                boolean isActief = rs.getBoolean("isActief");
                int factuurAdres = rs.getInt("factuurAdres"); //TODO Call AdresDAO instead
                int klant = rs.getInt("klant"); //TODO Call KlantDAO instead

                result.add(new Account(id, openDatum, isActief, factuurAdres, klant));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Account findById(int id) {
        Account result = null;

        try {

            PreparedStatement pstmt = connection.prepareStatement("SELECT openDatum, isActief, factuurAdres, klant FROM ACCOUNT WHERE account_id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LocalDate openDatum = rs.getDate("openDatum").toLocalDate();
                boolean isActief = rs.getBoolean("isActief");
                int factuurAdres = rs.getInt("factuurAdres"); //TODO Call AdresDAO instead
                int klant = rs.getInt("klant"); //TODO Call KlantDAO instead

                result = new Account(id, openDatum, isActief, factuurAdres, klant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void create(Account account) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO ACCOUNT(account_id, openDatum, isActief, factuurAdres, klant) VALUES(?,?,?,?,?) ");
            pstmt.setInt(1, account.getId());
            pstmt.setDate(2, Date.valueOf(account.getOpenDatum()));
            pstmt.setBoolean(3, account.getActief());
            pstmt.setInt(4, account.getFactuurAdres());
            pstmt.setInt(5, account.getKlant());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Account account) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("UPDATE ACCOUNT SET openDatum = ?, isActief = ?, factuurAdres = ?, klant = ? WHERE account_id = ?");
            pstmt.setDate(1, Date.valueOf(account.getOpenDatum()));
            pstmt.setBoolean(2, account.getActief());
            pstmt.setInt(3, account.getFactuurAdres());
            pstmt.setInt(4, account.getKlant());
            pstmt.setInt(5, account.getId());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Account account) {
        try {

            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM ACCOUNT WHERE account_id = ?");
            pstmt.setInt(1, account.getId());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
