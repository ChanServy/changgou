package com.chan.chang.goods.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "Template", value = "Template")
@TableName(value = "tb_template")
public class Template implements Serializable {

    @ApiModelProperty(value = "ID", required = false)
    @TableId(type = IdType.AUTO)
    private Integer id;//ID
    @ApiModelProperty(value = "模板名称", required = false)
    private String name;//模板名称
    @ApiModelProperty(value = "规格数量", required = false)
    private Integer specNum;//规格数量
    @ApiModelProperty(value = "参数数量", required = false)
    private Integer paraNum;//参数数量


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
    public Integer getSpecNum() {
        return specNum;
    }

    //set方法
    public void setSpecNum(Integer specNum) {
        this.specNum = specNum;
    }

    //get方法
    public Integer getParaNum() {
        return paraNum;
    }

    //set方法
    public void setParaNum(Integer paraNum) {
        this.paraNum = paraNum;
    }


}
