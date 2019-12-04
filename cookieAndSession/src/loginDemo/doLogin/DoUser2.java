package loginDemo.doLogin;

import loginDemo.domain.User;
import loginDemo.utils.JDBCUtils2;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class DoUser2 {
    private static JdbcTemplate template=new JdbcTemplate(JDBCUtils2.getDataSource());
    public static User doUser(User loginUser){
        String sql="select * from user where username=? and password=?";
        try {
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
