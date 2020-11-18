package cn.codenest.redists.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import cn.codenest.redists.entity.*;

import java.util.Date;
import java.util.List;

public interface GoodsPriceMapper {
    @Insert({
            "insert into goods_price (board, publish_date, ",
            "name, unit, price, ",
            "increaseofday, increaseofyear, ",
            "record_time)",
            "values (#{board,jdbcType=VARCHAR}, #{publishDate,jdbcType=DATE}, ",
            "#{name,jdbcType=VARCHAR}, #{unit,jdbcType=VARCHAR}, #{price,jdbcType=DOUBLE}, ",
            "#{increaseofday,jdbcType=DOUBLE}, #{increaseofyear,jdbcType=DOUBLE}, ",
            "#{recordTime,jdbcType=TIMESTAMP})"
    })
    int insert(GoodsPrice record);

    @Select({
            "select count(*) from goods_price where board=#{board,jdbcType=VARCHAR}" +
                    " and publish_date=#{publishDate,jdbcType=DATE}" +
                    " and name=#{name,jdbcType=VARCHAR}"
    })
    Integer countRecords(String board, Date publishDate, String name);

    @Select({"select id as id, board, publish_date as publishDate, " ,
            "name,unit,price,increaseofday,increaseofyear,record_time as recordTime " ,
                    "from goods_price where id= #{id,jdbcType=INTEGER}"})
    GoodsPrice selectById(Long id);

    @Select({"select id as id, board, publish_date as publishDate, ",
            "name,unit,price,increaseofday,increaseofyear,record_time as recordTime ",
            "from goods_price where board=#{board,jdbcType=VARCHAR} and publish_date=#{publishDate,jdbcType=DATE} and ",
            "name=#{name,jdbcType=VARCHAR}"})
    List<GoodsPrice> selectGoods(@Param("board") String board, @Param("publishDate") Date publishDate, @Param("name") String name);

    @Select({"select board, publish_date as publishDate,name ",
            "from goods_price  GROUP BY board,publish_date,name"})
    List<GoodsPrice> groupRecord();
}