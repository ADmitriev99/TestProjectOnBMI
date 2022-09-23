package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLUtil {

    protected static Connection connection = null;

    public static Connection getInstance() throws Exception {
        if (connection == null) {
            String DB_URL = "jdbc:postgresql://localhost:5433/BMI";
            String USER = "postgres";
            String PASS = "user";
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        return connection;
    }

    private SQLUtil() {
    }
}
