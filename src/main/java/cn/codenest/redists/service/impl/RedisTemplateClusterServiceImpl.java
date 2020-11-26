package cn.codenest.redists.service.impl;

import cn.codenest.redists.service.RedisTemplateClusterService;
import cn.hutool.core.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author ：Hyman
 * @date ：Created in 2020/11/26 16:46
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class RedisTemplateClusterServiceImpl implements RedisTemplateClusterService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void setStr(String key, String str) {
        try {
            redisTemplate.opsForValue().set(key, str);
            str = Convert.toStr(redisTemplate.opsForValue().get(key));
            System.out.println("RedisTemplateClusterService-name：" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
