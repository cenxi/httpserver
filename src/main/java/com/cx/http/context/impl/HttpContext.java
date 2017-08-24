package com.cx.http.context.impl;

import com.cx.http.context.Context;
import com.cx.http.context.Request;
import com.cx.http.context.Response;

import java.nio.channels.SelectionKey;

/**
 * @Title: HttpContext.java
 * @Description: HttpContext http上下文
 * @author: Han
 * @date: 2016年7月16日 下午2:20:00
 */
public class HttpContext extends Context {

    private Request request;
    private Response response;

    @Override
    public void setContext(String requestHeader, SelectionKey key) {

        //初始化request
        request = new HttpRequest(requestHeader);
        //初始化response
        response = new HttpResponse(key);
        setRequest();
        setResponse();
    }

    private void setRequest() {
        super.request = this.request;
    }

    private void setResponse() {
        super.response = this.response;
    }
}
