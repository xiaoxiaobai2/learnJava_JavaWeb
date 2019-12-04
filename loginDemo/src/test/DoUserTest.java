package test;

import doLogin.DoUser;
import domain.User;
import org.junit.Test;

public class DoUserTest {
    @Test
    public void testDoUser(){
        User loginUser=new User();
        loginUser.setUsername("zhang");
        loginUser.setPassword("1234567");
        System.out.println(DoUser.doUser(loginUser));
    }
}
