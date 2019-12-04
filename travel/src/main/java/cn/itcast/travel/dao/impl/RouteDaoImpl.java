package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 查找当前 目录的所有路线数
     * @return
     */
    @Override
    public int findTotalRoute(int cid, String rname) {

//        String sql = "select count(*) from tab_route where cid=?";
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        List parameterList = new ArrayList();
        if (cid!=0){
            sb.append(" and cid=? ");
            parameterList.add(cid);
        }
        if (rname!=null&&rname.length()>0){
            sb.append(" and rname like ? ");
            parameterList.add("%" +rname+"%");
        }

        return template.queryForObject(sb.toString(), Integer.class, parameterList.toArray());
    }

    @Override
    public List<Route> findRouteByPage(int cid,int start,int pageSize,String rname) {
//        String sql = "select * from tab_route where cid=? limit ?,?";

        /**
         * 粘贴时一定要注意！！！！！！！！    多加了个  from   ！！！
         */
        String sql = "select * from tab_route where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        List parameterList = new ArrayList();
        if (cid!=0){
            sb.append(" and cid=? ");
            parameterList.add(cid);
        }
        if (rname!=null&&rname.length()>0){
            sb.append(" and rname like ? ");
            parameterList.add("%" +rname+"%");
        }
        sb.append(" limit ?,?");
        parameterList.add(start);
        parameterList.add(pageSize);
        List<Route> routeList = template.query(sb.toString(), new BeanPropertyRowMapper<Route>(Route.class), parameterList.toArray());
        return routeList;
    }

    /**
     * 根据rid查询线路
     * @param rid
     * @return
     */
    @Override
    public Route findRouteById(int rid) {
        String sql = "select * from tab_route where rid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }

    @Override
    public void updateCount(int rid) {
        Route route = findRouteById(rid);
        String sql = "update tab_route set count=? where rid=?";
        template.update(sql,route.getCount()+1,rid);
    }

    /**
     * 查询符合要求的所有线路
     * @param cid
     * @param maxPrice
     * @param minPrice
     * @param rname
     * @return
     */
    @Override
    public int findAllRouteNum(int cid, int maxPrice, int minPrice, String rname) {
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        List parameterList = new ArrayList();
        if (cid!=0){
            sb.append(" and cid=? ");
            parameterList.add(cid);
        }
        if (rname!=null&&rname.length()>0&&!rname.equalsIgnoreCase("null")){
            sb.append(" and rname like ? ");
            parameterList.add("%" +rname+"%");
        }
        if (!(maxPrice<0)){
            sb.append(" and price<? ");
            parameterList.add(maxPrice);
        }
        if (!(minPrice<0)){
            sb.append(" and price>? ");
            parameterList.add(minPrice);
        }
//        System.out.println("parameterList = " + parameterList);
//        List<Route> query = template.query(sb.toString(), new BeanPropertyRowMapper<Route>(Route.class), parameterList.toArray());
//        for (Route route : query) {
//            System.out.println(route.toString());
//        }
//        return 16;
        return template.queryForObject(sb.toString(), Integer.class, parameterList.toArray());
    }

    /**
     * 查询指定条数 符合要求的线路，
     * @param cid
     * @param maxPrice
     * @param minPrice
     * @param rname
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Route> findAllRoutePage(int cid, int maxPrice, int minPrice, String rname, int start, int pageSize) {
        System.err.println("RouteDaoImpl.findAllRoutePage");
        String sql = "select * from tab_route where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        List parameterList = new ArrayList();
        if (cid!=0){
            sb.append(" and cid=? ");
            parameterList.add(cid);
        }
        if (rname!=null&&rname.length()>0&&!rname.equalsIgnoreCase("null")){
            sb.append(" and rname like ? ");
            parameterList.add("%" +rname+"%");
        }
        if (!(maxPrice<0)){
            sb.append(" and price<? ");
            parameterList.add(maxPrice);
        }
        if (!(minPrice<0)){
            sb.append(" and price>? ");
            parameterList.add(minPrice);
        }
        sb.append(" ORDER BY count desc");
        sb.append(" limit ?,? ");
        parameterList.add(start);
        parameterList.add(pageSize);
        for (Object o : parameterList) {
            System.out.println("o = " + o);
        }
        List<Route> routeList = template.query(sb.toString(), new BeanPropertyRowMapper<Route>(Route.class), parameterList.toArray());
        for (Route route : routeList) {
            System.out.println("parameter = " + route.toString());
        }
        return routeList;
    }

}
