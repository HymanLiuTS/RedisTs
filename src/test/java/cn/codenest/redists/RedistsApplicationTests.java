package cn.codenest.redists;

import cn.codenest.mybatis.entity.GoodsPrice;
import cn.codenest.redists.service.impl.GoodsServiceImpl;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.ContextLoader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedistsApplicationTests {

    @Autowired
    GoodsServiceImpl goodsServiceImpl;

    @Test
    public void selectById() {
        GoodsPrice goodsPrice = goodsServiceImpl.selectById(1L);
        System.out.println(JSON.toJSONString(goodsPrice));
    }

    @Test
    public void multiWriteGoods() {
        goodsServiceImpl.multiWriteGoods();
    }

    @Test
    public void multiWriteGoods2() {
        goodsServiceImpl.multiWriteGoods2();
    }


    @Test
    public void objectUtilsTS() {
        String str=ObjectUtils.getDisplayString("");
        System.out.println(str);
        Assert.hasText(str);
        str=ObjectUtils.getDisplayString(null);
        System.out.println(str);
        str=ObjectUtils.getDisplayString("123");
        System.out.println(str);
        str=ObjectUtils.getDisplayString(234);
        System.out.println(str);

    }

    @Test
    public void forName()  {
        try {
            Class<?> c = ClassUtils.forName("cn.codenest.redists.service.impl.GoodsServiceImpl", ContextLoader.class.getClassLoader());
            GoodsServiceImpl goodsServiceImpl= (GoodsServiceImpl)BeanUtils.instantiateClass(c);
            GoodsPrice goodsPrice=goodsServiceImpl.selectById(1L);
            System.out.println(JSON.toJSONString(goodsPrice));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            
        }
    }

}
