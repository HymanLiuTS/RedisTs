package cn.codenest.redists.service;

import cn.codenest.mybatis.entity.GoodsPrice;

import java.util.Date;
import java.util.List;

/**
 * <h3>springbootts</h3>
 * <p></p>
 *
 * @author : 你的名字
 * @date : 2020-08-14 12:19
 **/
public interface GoodsService {

    GoodsPrice selectById(Long id);

    List<GoodsPrice> selectGoods(String board, Date publishDate, String name);

    void multiWriteGoods();

    void multiWriteGoods2();
}
