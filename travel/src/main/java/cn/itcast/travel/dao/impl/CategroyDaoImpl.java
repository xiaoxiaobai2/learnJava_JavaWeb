package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategroyDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategroyDaoImpl implements CategroyDao {

    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 查询所有目录
     * @return
     */
    @Override
    public List findAll() {
        String sql = "select * from tab_category";
        List<Category> categoryList=null;
        try {
            categoryList = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        }catch (Exception e){
            e.printStackTrace();
        }
        return categoryList;
    }
}
