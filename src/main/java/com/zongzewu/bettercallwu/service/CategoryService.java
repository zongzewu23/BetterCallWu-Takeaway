package com.zongzewu.bettercallwu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zongzewu.bettercallwu.entity.Category;

public interface CategoryService extends IService<Category> {

    public void remove(Long id);
}
