package cn.codenest.redists.service.impl;

import cn.codenest.redists.service.JedisClusterService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ：Hyman
 * @date ：Created in 2020/11/26 16:31
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class JedisClusterServiceImpl implements JedisClusterService {

    JedisCluster cluster = null;

    public JedisClusterServiceImpl() {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("147.92.72.151", 6479));
        nodes.add(new HostAndPort("147.92.72.151", 6480));
        nodes.add(new HostAndPort("147.92.72.151", 6481));
        nodes.add(new HostAndPort("147.92.72.151", 6482));
        nodes.add(new HostAndPort("147.92.72.151", 6483));
        nodes.add(new HostAndPort("147.92.72.151", 6484));
        cluster = new JedisCluster(nodes);
    }

    @Override
    public void setStr(String key, String str) {
        String r = cluster.set(key, str);
        r = cluster.get(key);
        System.out.println(r);
    }
}
