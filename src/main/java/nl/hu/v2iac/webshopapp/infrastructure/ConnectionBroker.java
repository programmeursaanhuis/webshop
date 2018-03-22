package nl.hu.v2iac.webshopapp.infrastructure;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

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
