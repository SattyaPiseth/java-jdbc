package connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionPool {
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        try (InputStream input = ConnectionPool.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            config.setJdbcUrl(prop.getProperty("jdbcUrl"));
            config.setUsername(prop.getProperty("username"));
            config.setPassword(prop.getProperty("password"));
            ds = new HikariDataSource(config);
        } catch (IOException ex) {
            throw new RuntimeException("Error loading database properties", ex);
        }
    }

    private ConnectionPool() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}