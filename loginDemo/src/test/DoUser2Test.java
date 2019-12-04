package test;

import doLogin.DoUser2;
import domain.User;
import org.junit.Test;

public class DoUser2Test {
    @Test
    public void testDoUser(){
        User loginUser=new User();
        loginUser.setUsername("zhang");
        loginUser.setPassword("123456");
        System.out.println(DoUser2.doUser(loginUser));
    }
}
