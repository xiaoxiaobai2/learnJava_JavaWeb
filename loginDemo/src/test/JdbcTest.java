package test;

import org.junit.Test;
import utils.JDBCUtils2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTest {
    @Test
    public void testJDBC() throws SQLException {
        Connection conn = JDBCUtils2.getConn();
        String sql="select * from user where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"1");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
