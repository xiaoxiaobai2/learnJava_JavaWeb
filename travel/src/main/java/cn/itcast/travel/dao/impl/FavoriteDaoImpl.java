package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class FavoriteDaoImpl implements FavoriteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 根据 rid 和 uid 查询 favorite
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public Favorite findById(int rid, int uid) {
        String sql = "select * from tab_favorite where rid=? and uid=?";
        Favorite favorite=null;
        try {
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        }catch (Exception e){
            System.err.println("没有查询到Favorite(当前用户 没有收藏该路线)");
        }
        return favorite;
    }

    /**
     * 添加一条新的收藏记录
     * @param favorite
     */
    @Override
    public void add(Favorite favorite) {
        String sql = " insert into tab_favorite(rid,date,uid) values(?,?,?)";
        template.update(sql,favorite.getRid(),favorite.getDate(),favorite.getUid());
    }

    @Override
    public List findByuid(int uid) {
        String sql = "select * from tab_favorite where uid=?";
        return template.query(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),uid);
    }


}
