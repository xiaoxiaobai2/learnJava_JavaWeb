package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.impl.ProvinceDaoImpl;
import redis.clients.jedis.Jedis;
import service.ProvinceService;
import utils.JedisPoolUtils;

import java.util.List;

public class ProvinceServiceImp implements ProvinceService {
    @Override
    public List findAll() {
        ProvinceDaoImpl provinceDao = new ProvinceDaoImpl();
        return provinceDao.findAll();
    }

    @Override
    public String findAllByRedis() {
        Jedis jedis = JedisPoolUtils.getJedis();
        String province = jedis.get("province");
        if (province==null||province.length()==0){
            System.out.println("从数据中查询。。。。");
            List list = findAll();
            ObjectMapper mapper = new ObjectMapper();
            try {
                String json=mapper.writeValueAsString(list);
                jedis.set("province",json);
                return json;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("从redis中读取。。。");
            return province;
        }
        return null;
    }
}
