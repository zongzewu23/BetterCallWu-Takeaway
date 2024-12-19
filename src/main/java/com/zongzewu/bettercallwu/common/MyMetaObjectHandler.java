package com.zongzewu.bettercallwu.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;


/**
 * Custom metadata handler
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info(metaObject.toString());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info(metaObject.toString());

    }
}
