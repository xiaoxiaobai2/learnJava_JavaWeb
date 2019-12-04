package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

public interface SellerDao {
    /**
     * 根据卖家sid查询卖家信息
     * @return
     */
    Seller findById(int sid);
}
