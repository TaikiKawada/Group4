package utils;
 
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
 
public class Db {
 
    public static Connection getConnection() throws SQLException, NamingException {
    	Context initContext = new InitialContext();
    	Context envContext = (Context) initContext.lookup("java:/comp/env");
    	DataSource ds = (DataSource) envContext.lookup("jdbc/mariadb");
    	Connection con = ds.getConnection();
 
        return con;
    }
 
    public static void close(Connection c) throws SQLException {
        if (c != null && !c.isClosed()) {
            c.close();
        }
    }
}