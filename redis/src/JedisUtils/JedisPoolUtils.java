package JedisUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.*;
import java.util.Properties;

/**
 * jedis 获取连接工具类
 */
public class JedisPoolUtils {
    private static Jedis jedis;

    /**
     * 静态代码块,当该对象被使用时初始化连接池
     */
    static {
        //创建properties 对象
        Properties pro = new Properties();
        try {
            //获取文件
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("jedis.properties");
            //关联文件
            pro.load(is);

            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

            //获取配置信息设置到jedisPoolConfig
            jedisPoolConfig.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
            jedisPoolConfig.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
            //创建连接池对象
            JedisPool jedisPool = new JedisPool(jedisPoolConfig,pro.getProperty("host"),Integer.parseInt(pro.getProperty("port")));

            jedis = jedisPool.getResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Jedis getJedis(){
        return jedis;
    }

    public static void closeJedis(Jedis jedis){
        jedis.close();
    }
}
