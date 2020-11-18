package cn.codenest.redists.service.impl;

import cn.codenest.redists.bloom.MyBloomFilter;
import cn.codenest.redists.entity.GoodsPrice;
import cn.codenest.redists.mapper.GoodsPriceMapper;
import cn.codenest.redists.service.GoodsService;
import cn.codenest.redists.util.TimeFunc;
import cn.codenest.redists.util.ZipUtils;
import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Hyman
 * @date ：Created in 2020/8/14 12:23
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    GoodsPriceMapper goodsPriceMapper;

    @Autowired
    MyBloomFilter bloomFilter;

    @Override
    public GoodsPrice selectById(Long id) {
        GoodsPrice goodsPrice;
        String key = String.format("goods:id:%s", id);
        if (redisTemplate.hasKey(key)) {
            Object goods = redisTemplate.opsForValue().get(key);
            goodsPrice = JSON.parseObject(Convert.toStr(goods), GoodsPrice.class);
        } else {
            goodsPrice = goodsPriceMapper.selectById(id);
            redisTemplate.opsForValue().set(key, JSON.toJSONString(goodsPrice), 30, TimeUnit.MINUTES);
        }
        return goodsPrice;
    }

    @Override
    public List<GoodsPrice> selectGoods(String board, Date publishDate, String name) {
        List<GoodsPrice> result = null;
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String key = String.format("goods:info:%s_%s_%s", board, TimeFunc.AddDate(publishDate, Calendar.HOUR, -8).getTime(), name);
        if (redisTemplate.hasKey(key)) {
            Object goods = redisTemplate.opsForValue().get(key);
            String goodsStr = ZipUtils.unzip(Convert.toStr(goods));
            result = JSON.parseArray(goodsStr, GoodsPrice.class);
        } else {
            if (bloomFilter.isContainKey(key) == false) {
                return null;
            } else {
                result = goodsPriceMapper.selectGoods(board, publishDate, name);
                String compress = ZipUtils.zip(JSON.toJSONString(result));
                redisTemplate.opsForValue().set(key, compress, 1, TimeUnit.MINUTES);
            }
        }
        return result;
    }

    @Override
    public void multiWriteGoods() {

        ValueOperations<String, String> vo = redisTemplate.opsForValue();
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.multi();
        vo.set("key1","values1");
        vo.set("key2","values2");
        vo.set("key3","values3");
        vo.set("key4","values4");
        redisTemplate.exec();
    }

    @Override
    public void multiWriteGoods2() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxWaitMillis(20000);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.100.90",6379,2000,"123");
        Jedis jedis = jedisPool.getResource();
        long start = System.currentTimeMillis();
        // 开启流水线
        Pipeline pipeline = jedis.pipelined();
        pipeline.multi();
        // 测试10w条数据读写
        for(int i = 0; i < 100000; i++) {
            int j = i + 1;
            pipeline.set("key" + j, "value" + j);
            pipeline.get("key" + j);
        }
        // 只执行同步但不返回结果
        //pipeline.sync();
        // 以list的形式返回执行过的命令的结果
        List<Object> result = pipeline.syncAndReturnAll();
        pipeline.exec();
        long end = System.currentTimeMillis();
        // 计算耗时
        System.out.println("耗时" + (end - start) + "毫秒");
    }
}
