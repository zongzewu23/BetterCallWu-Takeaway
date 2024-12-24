package com.zongzewu.bettercallwu.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * User info entity
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    // User ID
    private Long id;

    // Name
    private String name;

    // Email
    private String email;

    // Gender: 0 for female, 1 for male
    private String sex;

    // ID number
    private String idNumber;

    // Avatar
    private String avatar;

    // Status: 0 for disabled, 1 for active
    private Integer status;
}
