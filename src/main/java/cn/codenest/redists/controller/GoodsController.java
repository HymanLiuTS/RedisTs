package cn.codenest.redists.controller;

import cn.codenest.redists.dto.GoodsQueryDto;
import cn.codenest.redists.service.GoodsService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ：Hyman
 * @date ：Created in 2020/8/14 12:25
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/business/api/goods/v1")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/id/{id}")
    public Object getGoodsByID(@PathVariable Long id) {
        try {
            return goodsService.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    @PostMapping("/info")
    public Object getGoods(@Valid @RequestBody GoodsQueryDto dto) {
        try {
            //System.out.println(JSON.toJSONString(dto));
            return goodsService.selectGoods(dto.getBoard(), dto.getPublishDate(), dto.getName()).size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

}
