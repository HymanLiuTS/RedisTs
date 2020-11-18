package cn.codenest.redists.service.impl;

import cn.codenest.redists.service.RedisTemplateSentinelService;
import cn.hutool.core.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author ：Hyman
 * @date ：Created in 2020/11/18 15:29
 * @description：springboot集成哨兵模式下的redis
 * @modified By：
 * @version: $
 */
@Service
public class RedisTemplateSentinelServiceImpl implements RedisTemplateSentinelService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void setStr(String key, String str) {
        try {
            redisTemplate.opsForValue().set(key, str);
            str= Convert.toStr(redisTemplate.opsForValue().get(key));
            System.out.println("RedisTemplateSentinelService-name：" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
