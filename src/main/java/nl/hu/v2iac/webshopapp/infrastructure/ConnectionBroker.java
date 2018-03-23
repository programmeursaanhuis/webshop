package nl.hu.v2iac.webshopapp.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBroker {
    private String connectionURL = "jdbc:postgresql://localhost:5432/webshop";
    private String username = "postgres";
    private String password = "password";
    private String driver = "org.postgresql.Driver";
    private Connection con;

    ConnectionBroker() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    Connection getConnection(){
        // URL, User and Password
        try {
			con = DriverManager.getConnection(connectionURL, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return con;
    }

    void close() throws SQLException {
        if (con != null) {
            con.close();
        }
    }
}
