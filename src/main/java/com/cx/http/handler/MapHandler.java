package com.cx.http.handler;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Element;

import com.cx.http.utils.XMLUtil;

/**
 * @Title:  HandlerMap.java
 * @Description: HandlerMap(单例) 访问路径--->相应解决类
 * @author: Han
 * @date:   2016年7月15日 下午4:52:29  
 */
public class MapHandler {

    //访问路径对应控制类
    private static Map<String, Handler> handlerMap = new HashMap<>();

    private static MapHandler instance = null;

    //将构造器私有化
    private MapHandler(){}

    //得到HandlerMap对象实例
    public static MapHandler getContextMapInstance() {

        if(instance == null) {
            synchronized (MapHandler.class) {
                if(instance == null) {
                    instance = new MapHandler();
                    //得到web.xml的根路径
                    String xmlPath = "";
                    try {
                        xmlPath = URLDecoder.decode(MapHandler.class.getClassLoader().getResource("web.xml").getPath(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Element rootElement = XMLUtil.getRootElement(xmlPath);
                    //得到handler的集合
                    List<Element> handlers = XMLUtil.getElements(rootElement);
                    for (Element element : handlers) {
                        Element urlPattenEle = XMLUtil.getElement(element, "url-patten");
                        //得到urlPatten(uri)
                        String urlPatten = XMLUtil.getElementText(urlPattenEle);
                        Element handlerClazzEle = XMLUtil.getElement(element, "handler-class");
                        //得到handler 的class文件路径
                        String clazzPath = XMLUtil.getElementText(handlerClazzEle);
                        Class<?> clazz = null;
                        try {
                            //通过反射得到handler实例化对象，然后以键值对的形式存储
                            clazz = Class.forName(clazzPath);
                            Handler handler = (Handler)clazz.newInstance();
                            instance.getHandlerMap().put(urlPatten, handler);
                            Logger.getLogger(MapHandler.class).info("成功添加Handler " + clazzPath);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return instance;
    }

    public Map<String, Handler> getHandlerMap() {
        return handlerMap;
    }
}
