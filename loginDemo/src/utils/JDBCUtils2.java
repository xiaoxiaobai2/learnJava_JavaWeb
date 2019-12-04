package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils2 {
    private static DataSource dataSource;
    static {
        try {
            Properties properties=new Properties();
            InputStream is = JDBCUtils2.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);
            dataSource= DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConn() throws SQLException {
        return dataSource.getConnection();
    }
}
