package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    int findTotalRoute(int cid, String rname);
    List<Route> findRouteByPage(int cid,int start,int pageSize, String rname);

    /**
     * 根据rid查询线路
     * @param rid
     * @return
     */
    Route findRouteById(int rid);

    /**
     * 该线路被收藏的次数+1
     */
    void updateCount(int rid);

    int findAllRouteNum(int cid,int maxPrice,int minPrice,String rname);

    /**
     * 查询指定条数 符合要求的线路，
     * @param cid
     * @param maxPrice
     * @param minPrice
     * @param rname
     * @return
     */
    List<Route> findAllRoutePage(int cid,int maxPrice,int minPrice,String rname,int start,int pageSize);
}
