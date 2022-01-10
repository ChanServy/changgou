package com.chan.chang.goods.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "Brand", value = "Brand")
@Data
@TableName(value = "tb_brand")
public class Brand implements Serializable {

    @ApiModelProperty(value = "品牌id", required = false)
    @TableId(type = IdType.AUTO)
    private Integer id;//品牌id

    @ApiModelProperty(value = "品牌名称", required = false)
    private String name;//品牌名称

    @ApiModelProperty(value = "品牌图片地址", required = false)
    private String image;//品牌图片地址

    @ApiModelProperty(value = "品牌的首字母", required = false)
    private String letter;//品牌的首字母

    @ApiModelProperty(value = "排序", required = false)
    private Integer seq;//排序

}
