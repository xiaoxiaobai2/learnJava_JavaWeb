package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.User;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;


/**
 * 测试java 和 json 互相转化
 */
public class TestJson {
    @Test
    public void test() throws IOException {
        User user = new User();
        user.setName("张三");
        user.setAge(20);
        user.setGender("男");
        user.setBirthday(new Date());

        //创建Jackson核心对象 ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        //转换为字符串格式
        String json = objectMapper.writeValueAsString(user);
        //转换为字符串并存储到指定文件
//        objectMapper.writeValue(new File("d:user.txt"),user);
        System.out.println(json);
    }

    @Test
    public void test2() throws IOException {
        String s= "{\"name\":\"张三\",\"age\":20,\"gender\":\"男\",\"birthday\":\"19年11月06日\"}";
        //创建核心对象
        ObjectMapper objectMapper = new ObjectMapper();
        //json转java
        User user = objectMapper.readValue(s,User.class);
        System.out.println(user.toString());
    }
}
