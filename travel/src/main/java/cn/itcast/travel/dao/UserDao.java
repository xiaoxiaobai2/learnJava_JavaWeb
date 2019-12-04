package cn.itcast.travel.dao;


import cn.itcast.travel.domain.User;

public interface UserDao {
    User findByUsername(String username);
    void insertUser(User user);
    User findByCode(String code);
    void updateUserStatus(User user);

    User findUserByUsernameAndPassword(String username, String password);
}
