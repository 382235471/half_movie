package com.half.movie.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component
public class UpdateRelatedFieldsMetaHandler implements MetaObjectHandler {

    /**
     * 新增操作
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //this.strictInsertFill(metaObject, "createId", String.class, getCurrentUserId());
        //this.strictInsertFill(metaObject, "creationtime", LocalDateTime.class, LocalDateTime.now());
        //this.strictInsertFill(metaObject, "updateId", String.class, getCurrentUserId());
        //this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

        this.setFieldValByName("creationtime",  new Date(), metaObject);
        this.setFieldValByName("lastlogintime", new Date(), metaObject);
    }

    /**
     * 更新操作
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        //this.strictUpdateFill(metaObject, "updateId", String.class, getCurrentUserId());
        //this.strictUpdateFill(metaObject, "modifiedtime", LocalDateTime.class, LocalDateTime.now());
        this.setFieldValByName("modifiedtime",  new Date(), metaObject);
        this.setFieldValByName("lastlogintime", new Date(), metaObject);
    }

    /**
     * 获取当前登录用户ID
     *
     * @return
     */
    private String getCurrentUserId() {
        return "当前登录用户ID";
    }
}
