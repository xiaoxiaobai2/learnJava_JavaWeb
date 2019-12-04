import JedisUtils.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis测试类
 */
public class TestRedis {
    /**
     * jedis 入门
     */
    @Test
    public void test01() {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.set("username","zhangsan");
        jedis.close();
    }

    @Test
    public void test02(){
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.set("username","zhangsan");
        String username = jedis.get("username");
        System.out.println(username);
    }


}
