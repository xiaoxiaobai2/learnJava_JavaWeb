package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user = null;
        /*
            记住此处时手动需要加的，否则会报空指针异常！！！！！！！！！！！！！
         */
        try {
            //1.定义sql
            String sql = "select * from tab_user where username = ?";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {

        }
        System.out.println(user);
        return user;
    }

    @Override
    public void insertUser(User user) {
        //1.定义sql
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        //2.执行sql
        System.out.println(user.toString());
        template.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
        );
    }

    @Override
    public User findByCode(String code) {
        String sql="select * from tab_user where code=?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 更新状态码
     * @param user
     */
    @Override
    public void updateUserStatus(User user) {
        String sql="update tab_user set status=? where uid=?";
        try {
            template.update(sql,user.getStatus(),user.getUid());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过用户名和密码查找用户
     * @param username
     * @param password
     * @return 完整的用户信息  包括状态码等
     */
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        String sql="select * from tab_user where username=? and password=?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username,password);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }
}
