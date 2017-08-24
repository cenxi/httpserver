package com.cx.http.handler.impl;

import org.apache.log4j.Logger;

import com.cx.http.context.Context;
import com.cx.http.handler.abs.AbstractHandler;

/**
 * @Title:  LogionHandler.java
 * @Description: 解决login业务逻辑
 * @author: Han
 * @date:   2016年7月16日 下午2:08:18  
 */
public class LoginHandler extends AbstractHandler{

    private Logger logger = Logger.getLogger(LoginHandler.class);

    @Override
    public void doGet(Context context) {
        logger.info("进入了handler--->LoginHandler");
        context.getResponse().setHtmlFile("login.html");
    }
}
