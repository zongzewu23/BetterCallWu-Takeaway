package com.zongzewu.bettercallwu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * address book
 */
@Data
public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    // User ID
    private Long userId;

    // Recipient
    private String consignee;

    // Phone number
    private String phone;

    // Gender: 0 Female, 1 Male
    private String sex;

    // State code
    private String stateCode;

    // State name
    private String stateName;

    // City code
    private String cityCode;

    // City name
    private String cityName;

    // District code (e.g., county or borough)
    private String districtCode;

    // District name
    private String districtName;

    // Street address
    private String detail;

    // ZIP code
    private String zipcode;

    // Label (e.g., Home, Work)
    private String label;

    // Default address: 0 No, 1 Yes
    private Integer isDefault;

    // Creation time
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    // Update time
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    // Created by
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    // Updated by
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    // Is deleted: 0 No, 1 Yes
    private Integer isDeleted;
}
