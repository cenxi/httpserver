package com.cx.http.context;

import com.cx.http.utils.XMLUtil;

import java.nio.channels.SelectionKey;

public interface Response {

    //服务器名字
    public static final String SERVER_NAME = XMLUtil.getRootElement(Response.class.getClassLoader().getResource("server.xml").getPath()).element("serverName").getText();

    public String getContentType();

    public int getStatuCode();

    public String getStatuCodeStr();

    public String getHtmlFile();

    public void setHtmlFile(String htmlFile);

    public SelectionKey getKey();

    public void setContentType(String contentType);

    public void setStatuCode(int statuCode);

    public void setStatuCodeStr(String statuCodeStr);
}


