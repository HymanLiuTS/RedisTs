package cn.codenest.redists.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ：Hyman
 * @date ：Created in 2020/8/15 11:53
 * @description：
 * @modified By：
 * @version: $
 */
@Data
public class GoodsQueryDto {

    @NotEmpty(message ="行业不能为空")
    private String board;

    @NotNull(message ="发布时间不能为null")
    private Date publishDate;

    @NotEmpty(message ="名称不能为空")
    private String name;
}
