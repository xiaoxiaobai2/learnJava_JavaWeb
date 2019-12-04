package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategroyDao;
import cn.itcast.travel.dao.impl.CategroyDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategroyService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategroyServiceImpl implements CategroyService {

    /**
     * 查询所有目录
     * 并用redis 进行缓存处理
     * @return
     */
    private CategroyDao categroyDao = new CategroyDaoImpl();
    @Override
    public List findAll() {
        System.out.println("CategroyServiceImpl.findAll");
        Jedis jedis = JedisUtil.getJedis();
        //获取链接超时
        if (jedis==null){
            return categroyDao.findAll();
        }
        //获取缓存的 菜单目录
//        Set<String> categorySet = jedis.zrange("category", 0, -1);
        Set<Tuple> tuples = jedis.zrangeWithScores("category", 0, -1);
        List<Category> categoryList = null;

        //没有缓存数据，则去数据库查找，并且进行缓存
        if (tuples == null ||tuples.size()==0){
            System.out.println("CategroyServiceImpl.findAll----通过数据库获取数据");
            categoryList = categroyDao.findAll();
            for (Category c : categoryList) {
                System.out.println(c.getCid()+"---------"+c.getCname());
                jedis.zadd("category",c.getCid(),c.getCname());
            }
        }else {
            //缓存中有数据，直接读取， 并且将格式转化为list
            System.out.println("CategroyServiceImpl.findAll----缓存获取数据");
            categoryList = new ArrayList<>();
            for (Tuple tuple : tuples) {

                Category category = new Category();
                category.setCid((int)tuple.getScore());
                category.setCname(tuple.getElement());

                categoryList.add(category);
            }
        }
        return categoryList;
    }
}
