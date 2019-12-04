package dao.impl;

import dao.ProvinceDao;
import domain.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用maven创建一个小案例
 * 数据库连接测试
 */
public class ProvinceDaoImpl implements ProvinceDao {
    public List<Province> findAll() {
        List<Province> provinceList=new ArrayList<Province>();
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //获取connection对象
            connection = DriverManager.getConnection("jdbc:mysql:///day23", "root", "123456");
            //获取真正操作数据的对象
            preparedStatement = connection.prepareStatement("select * from province");
            //执行数据库查询操作
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Province province = new Province();
                province.setName(resultSet.getString("name"));
                province.setID(resultSet.getInt("id"));
                provinceList.add(province);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return provinceList;
    }
}
