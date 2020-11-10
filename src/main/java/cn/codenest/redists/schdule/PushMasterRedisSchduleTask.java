package cn.codenest.redists.schdule;

import cn.codenest.redists.util.TimeUtil;
import cn.hutool.core.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ：Hyman
 * @date ：Created in 2020/8/12 8:51
 * @description：
 * @modified By：
 * @version: $
 */

//@Component
public class PushMasterRedisSchduleTask implements SchduleTask {

    private RedisTemplate redisTemplate;
    public static AtomicLong i = new AtomicLong(0);

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    @Scheduled(cron = "*/1 * * * * ?")
    @Override
    public void execute() {
        System.out.println("定时任务开始......");
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    redisTemplate.opsForValue().set("count:" + Convert.toStr(PushMasterRedisSchduleTask.i.incrementAndGet()), TimeUtil.timestamp2DateStr(begin, "yyyy-MM-dd HH-mm-ss"));
                }
            });
            t.start();
        }

        long end = System.currentTimeMillis();
        System.out.println("定时任务结束......");
    }
}
