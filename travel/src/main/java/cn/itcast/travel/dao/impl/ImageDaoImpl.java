package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.ImageDao;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


public class ImageDaoImpl implements ImageDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 根据rid查询线路图片列表
     * @param rid
     * @return
     */
    @Override
    public List findImg(int rid) {
        String sql = "select * from tab_route_img where rid=?";

        List<RouteImg> routeImgList = template.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);
        return routeImgList;
    }
}
