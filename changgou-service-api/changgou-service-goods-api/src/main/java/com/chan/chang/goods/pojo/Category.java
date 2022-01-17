package com.chan.chang.goods.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "Category", value = "Category")
@TableName(value = "tb_category")
public class Category implements Serializable {

    @ApiModelProperty(value = "分类ID", required = false)
    @TableId(type = IdType.AUTO)
    private Integer id;//分类ID
    @ApiModelProperty(value = "分类名称", required = false)
    private String name;//分类名称
    @ApiModelProperty(value = "商品数量", required = false)
    private Integer goodsNum;//商品数量
    @ApiModelProperty(value = "是否显示", required = false)
    private String isShow;//是否显示
    @ApiModelProperty(value = "是否导航", required = false)
    private String isMenu;//是否导航
    @ApiModelProperty(value = "排序", required = false)
    private Integer seq;//排序
    @ApiModelProperty(value = "上级ID", required = false)
    private Integer parentId;//上级ID
    @ApiModelProperty(value = "模板ID", required = false)
    private Integer templateId;//模板ID


    //get方法
    public Integer getId() {
        return id;
    }

    //set方法
    public void setId(Integer id) {
        this.id = id;
    }

    //get方法
    public String getName() {
        return name;
    }

    //set方法
    public void setName(String name) {
        this.name = name;
    }

    //get方法
    public Integer getGoodsNum() {
        return goodsNum;
    }

    //set方法
    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    //get方法
    public String getIsShow() {
        return isShow;
    }

    //set方法
    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    //get方法
    public String getIsMenu() {
        return isMenu;
    }

    //set方法
    public void setIsMenu(String isMenu) {
        this.isMenu = isMenu;
    }

    //get方法
    public Integer getSeq() {
        return seq;
    }

    //set方法
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    //get方法
    public Integer getParentId() {
        return parentId;
    }

    //set方法
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    //get方法
    public Integer getTemplateId() {
        return templateId;
    }

    //set方法
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }


}
