package cn.itcast.travel.dao;


import cn.itcast.travel.domain.RouteImg;

import java.util.List;


public interface ImageDao {
    /**
     * 根据rid查询线路图片
     * @param rid
     * @return
     */
    List<RouteImg> findImg(int rid);
}
