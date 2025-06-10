package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        String url = "jdbc:mariadb://<192.168.5.168>:3306/Group4";
        String user = "root";
        String password = "1111";

        return DriverManager.getConnection(url, user, password);
    }

    public static void close(Connection c) throws SQLException {
        if (c != null && !c.isClosed()) {
            c.close();
        }
    }
}
