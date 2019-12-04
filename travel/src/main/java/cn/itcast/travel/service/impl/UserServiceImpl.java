package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean register(User user) {
        //按用户名查找用户
        User user_result = userDao.findByUsername(user.getUsername());
        user.setStatus("N");//设置激活状态为no
        user.setCode(UuidUtil.getUuid());//存储唯一的激活码
        if (user_result==null){
            System.out.println("UserServiceImpl.register");
            userDao.insertUser(user);
            /*
                注册完成之后发送邮件
             */

            //设置邮件内容，跳转到activeServlet
            String text="<a href='http://localhost/travel/user/active?code=" + user.getCode() + "'>激活邮箱</a>";
            System.out.println(text);
            //发送邮件（邮件地址，邮件内容，邮件主题）
            MailUtils.sendMail(user.getEmail(),text,"黑马旅游网，激活邮箱");

            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean checkCode(String code) {
        User user = userDao.findByCode(code);
        if (user!=null){
            //查找到用户，证明code存在，正常激活;
            user.setStatus("Y");
            userDao.updateUserStatus(user);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        return userDao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

}
