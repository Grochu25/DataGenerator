package mainPackage.DB;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    private static DBConnection instance;
    public static DBConnection getInstance() throws IOException, SQLException
    {
        if(instance == null)
            instance = new DBConnection();
        return instance;
    }

    private Connection connection;

    private DBConnection() throws IOException, SQLException {
        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("db.properties")))
        {
            props.load(in);
        }

        String drivers = props.getProperty("jdbc.drivers");
        String url = props.getProperty("jdbc.url");
        String user = props.getProperty("jdbc.user");
        String password = props.getProperty("jdbc.password");

        connection = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() { return connection; }
}
