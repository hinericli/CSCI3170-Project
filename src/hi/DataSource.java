package hi;

import java.sql.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
    	config.setJdbcUrl("jdbc:oracle:thin:@//db18.cse.cuhk.edu.hk:1521/oradb.cse.cuhk.edu.hk");
    	config.setUsername("h062");
    	config.setPassword("yogAkkef");
    	config.addDataSourceProperty("connectionTimeout", "1000");
    	config.addDataSourceProperty("idleTimeout", "6000");
    	config.addDataSourceProperty("maximumPoolSize", "10");
    	ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
