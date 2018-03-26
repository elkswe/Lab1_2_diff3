package catalog.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    private Connection connection;

    public SQLConnection() {
        connection = null;
    }

    public Connection getConnection() {
        try {
            if (connection == null) {
                String url = "jdbc:mysql://localhost/catalog?useUnicode=true&" +
                        "useJDBCCompliantTimezoneShift=true&" +
                        "useLegacyDatetimeCode=false&" +
                        "serverTimezone=UTC&" +
                        "useSSL=false";
                String user = "root";
                String password = "root";
                String driver = "com.mysql.jdbc.Driver";

                try {
                    Class.forName(driver).newInstance();
                    connection = DriverManager.getConnection(url, user, password);
                } catch (ClassNotFoundException e) {
                    throw new SQLException("Driver not loaded.", e);
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
