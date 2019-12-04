package test;

import cn.itcast.travel.util.MailUtils;
import org.junit.Test;

public class TestSendMail {
    @Test
    public void testSendMail(){
        MailUtils.sendMail("nwu_zhanghao@163.com","你好，这是一封测试邮件，无需回复！","测试邮件");
    }
}
