package cn.codenest.redists.service;

import cn.hutool.core.convert.Convert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ：Hyman
 * @date ：Created in 2020/11/26 16:51
 * @description：
 * @modified By：
 * @version: $
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateClusterServiceTest {

    @Autowired
    RedisTemplateClusterService redisTemplateClusterService;

    @Test
    public void setStrTest() throws InterruptedException {
        for (Integer i = 0; i < 100; i++) {
            redisTemplateClusterService.setStr("name" + Convert.toStr(i), i.toString());
            Thread.sleep(2000);
            System.out.println("++++++++++++++++++++++++++++++" + i);
        }

    }

}
