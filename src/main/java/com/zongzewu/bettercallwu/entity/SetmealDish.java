package com.zongzewu.bettercallwu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *combo and dish relationship
 */
@Data
public class SetmealDish implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    //combo id
    private Long setmealId;


    //dish id
    private Long dishId;


    //Dish Name (redundant field)
    private String name;

    //Original price of dishes
    private BigDecimal price;

    //Number of copies
    private Integer copies;


    //Sorting
    private Integer sort;


    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


    //Delete or not
    private Integer isDeleted;
}
