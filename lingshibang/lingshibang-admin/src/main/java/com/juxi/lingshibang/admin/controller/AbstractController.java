package com.juxi.lingshibang.admin.controller;

import com.juxi.lingshibang.admin.entity.User;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected User getUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    protected String getUserId() {
        return getUser().getId();
    }

//    protected Long getDeptId() {
//        return getUser().getDeptId();
//    }

}
