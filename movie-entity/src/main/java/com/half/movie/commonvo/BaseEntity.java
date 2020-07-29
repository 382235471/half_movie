package com.half.movie.commonvo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    //指定数据库主键字段,配合plus自动生成生成主键策略
    @TableId(value="id")
    public String id;
    @TableField(value = "creationtime", fill = FieldFill.INSERT) // 新增执行
    public Date creationtime;
    @TableField(value = "creator", fill = FieldFill.INSERT)
    public String creator;
    @TableField(value = "modifiedtime", fill = FieldFill.UPDATE) // 更新执行
    public Date modifiedtime;
    @TableField(value = "modifier", fill = FieldFill.UPDATE)
    public String modifier;

}
