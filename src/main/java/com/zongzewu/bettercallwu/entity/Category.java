package com.zongzewu.bettercallwu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * category
 */
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    // 1 dish category 2 combo category
    private Integer type;


    //category name
    private String name;


    //sort
    private Integer sort;


    //created time
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    //updated time
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    //user who creates this
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    //user modify
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


    //delete or not
    private Integer isDeleted;

}
