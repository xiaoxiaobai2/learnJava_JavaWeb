package test;

import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import org.junit.Test;

public class TestInsert {
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("zhanghao");
        user.setPassword("123456");
        user.setUsername("123456");
//        user.setBirthday("123456");
//        user.setCode("123456");
//        user.setEmail("123456");
//        user.setSex("1");
//        user.setStatus("1");
//        user.setTelephone("123456");
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.insertUser(user);
    }
}
