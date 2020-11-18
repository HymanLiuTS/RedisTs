package cn.codenest.redists.service.impl;

import cn.codenest.redists.service.JedisSentinelPoolService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ：Hyman
 * @date ：Created in 2020/11/18 10:49
 * @description：哨兵模式下，使用jedis连接redis哨兵
 * @modified By：
 * @version: $
 */
@Service
public class JedisSentinelPoolServiceImpl implements JedisSentinelPoolService {


    /**
     * 哨兵链接池
     */
    private JedisSentinelPool jedisSentinelPool;


    /**
     * 初始化哨兵池
     */
    private void initialJedisSentinelPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        //是否启用后进先出, 默认true
        config.setLifo(true);
        //最大空闲连接数, 默认8个
        config.setMaxIdle(8);
        //最大连接数, 默认8个
        config.setMaxTotal(8);
        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        config.setMaxWaitMillis(-1);
        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        config.setMinEvictableIdleTimeMillis(1800000);
        //最小空闲连接数, 默认0
        config.setMinIdle(0);
        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        config.setNumTestsPerEvictionRun(3);
        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
        config.setSoftMinEvictableIdleTimeMillis(1800000);
        //在获取连接的时候检查有效性, 默认false
        config.setTestOnBorrow(false);
        //在空闲时检查有效性, 默认false
        config.setTestWhileIdle(false);

        // 哨兵的IP和端口号
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("172.17.6.85:26479");
        sentinels.add("172.17.6.85:26480");
        sentinels.add("172.17.6.85:26481");
        // 构造池
        jedisSentinelPool = new JedisSentinelPool("mymaster", sentinels, config);

    }


    public JedisSentinelPoolServiceImpl() {
        try {
            initialJedisSentinelPool();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setStr(String key, String str) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            if (jedis != null) {

            }
            jedis.set(key, str);
            String name = jedis.get(key);
            System.out.println("jedisSentinelPool-name：" + name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
