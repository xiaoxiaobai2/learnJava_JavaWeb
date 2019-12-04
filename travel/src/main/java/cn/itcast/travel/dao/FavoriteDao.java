package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface FavoriteDao {
    /**
     * 根据 uid 和 rid  查询  Favorite
     * @param rid
     * @return
     */
    Favorite findById(int rid,int uid);

    /**
     * 添加一条新的收藏记录
     * @param favorite
     */
    void add(Favorite favorite);

    /**
     * 查找到 uid 对应的所有 rid
     * @param uid
     * @return
     */
    List<Favorite> findByuid(int uid);
}
