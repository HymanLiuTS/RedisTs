package cn.codenest.redists.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ：Hyman
 * @date ：Created in 2020/11/18 10:56
 * @description：
 * @modified By：
 * @version: $
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisSentinelPoolServiceTest {
    @Autowired
    JedisSentinelPoolService jedisSentinelPoolService;

    @Test
    public void setStrByJedisSentinelPoolServiceTest() throws InterruptedException {
        for (Integer i = 0; i < 100; i++) {
            jedisSentinelPoolService.setStr("name1", i.toString());
            Thread.sleep(2000);
            System.out.println("++++++++++++++++++++++++++++++" + i);
        }

    }

}
