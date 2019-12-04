package doLogin;

import domain.User;
import utils.JDBCutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoUser {
    public static User doUser(User loginUser){
        User user=new User();
        Connection conn = JDBCutils.getConn();
        String sql="select * from user where username=? and password=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,loginUser.getUsername());
            ps.setString(2,loginUser.getPassword());
            ResultSet rs = ps.executeQuery();
            boolean flag=false;
            while(rs.next()){
                flag=true;
                user.setUsername(rs.getString("username"));
                user.setID(rs.getInt("id"));
                user.setPassword(rs.getString("password"));
            }
            if (flag)
                return user;
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
