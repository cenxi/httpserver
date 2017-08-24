package com.cx.http.utils;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
public class XMLUtil {

    private static final Logger log = Logger.getLogger(XMLUtil.class);
    private static SAXReader sax = new SAXReader();

    public static Element getRootElement(String xmlPath) {
        Document document = null;
        try {
            document = sax.read(new File(xmlPath));
        } catch (DocumentException e) {
            log.error("找不到指定的xml文件--" + xmlPath + "--");
        }
        return document.getRootElement();
    }

    public static List<Element> getElements(Element element) {
        return element.elements();
    }

    public static Element getElement(Element element, String name) {
        Element childElement = element.element(name);
        if (childElement == null) {
            log.error(element.getName()+"节点下没有子节点"+name);
            return null;
        }
        return childElement;
    }

    /**
     * @param element
     * @return
     */
    public static String getElementText(Element element) {
        return element.getText();
    }
}
