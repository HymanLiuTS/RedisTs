package cn.codenest.redists.bloom;

import cn.codenest.redists.entity.*;
import cn.codenest.redists.mapper.GoodsPriceMapper;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author ：Hyman
 * @date ：Created in 2020/8/18 10:30
 * @description：
 * @modified By：
 * @version: $
 */

@Component
public class GoogleBloom implements MyBloomFilter {


    private BloomFilter<String> bloom = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 10000, 0.0000001);


    public GoogleBloom(@Autowired GoodsPriceMapper goodsPriceMapper) {
        List<GoodsPrice> goods = goodsPriceMapper.groupRecord();
        if (CollectionUtils.isEmpty(goods) == false) {
            goods.stream().forEach(x -> {
                String key = String.format("goods:info:%s_%s_%s", x.getBoard(), x.getPublishDate().getTime(), x.getName());
                putKey(key);
            });
        }
    }

    @Override
    public boolean isContainKey(String key) {
        boolean b = bloom.mightContain(key);
        return b;
    }

    @Override
    public void putKey(String key) {
        bloom.put(key);
        Boolean b = bloom.mightContain(key);
        System.out.println(String.format("%s:%s", key, b));
    }

    public static void main(String[] args) {
//      /*参数1：创建的过滤器的通道类型，和T保持一致即可；参数2：预计过滤器存储的数据条数；参数3：可容忍的错误率*/
        BloomFilter<String> b = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 10000, 0.0000001);
        b.put("123");
        b.put("456");
        System.out.println(b.mightContain("123"));
        System.out.println(b.mightContain("789"));
        BloomFilter<String> b1 = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 10000, 0.0000001);
        b1.put("qwer");
        b1.put("goods:info:农副_1596729600000_DDGS");
        b1.putAll(b);
        //System.out.println(b1.mightContain("123"));
        //System.out.println(b1.mightContain("goods:info:农副_1596758400000_DDGS"));
    }
}
