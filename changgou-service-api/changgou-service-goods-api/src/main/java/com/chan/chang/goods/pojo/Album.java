package com.chan.chang.goods.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName(value = "tb_album")
public class Album implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;//编号

    private String title;//相册名称

    private String image;//相册封面

    private String imageItems;//图片列表


    //get方法
    public Long getId() {
        return id;
    }

    //set方法
    public void setId(Long id) {
        this.id = id;
    }

    //get方法
    public String getTitle() {
        return title;
    }

    //set方法
    public void setTitle(String title) {
        this.title = title;
    }

    //get方法
    public String getImage() {
        return image;
    }

    //set方法
    public void setImage(String image) {
        this.image = image;
    }

    //get方法
    public String getImageItems() {
        return imageItems;
    }

    //set方法
    public void setImageItems(String imageItems) {
        this.imageItems = imageItems;
    }


}
