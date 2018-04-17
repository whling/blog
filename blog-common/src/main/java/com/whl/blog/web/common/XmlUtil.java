package com.whl.blog.web.common;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Xml 解析工具
 * Created by Liull on 2016/10/17.
 *
 */
public class XmlUtil {
    /**
     * @desc 取某一节点下的值
     * path: ROOT.b.c(叶子节点)
     */
    public static String getText(String xml,String path) throws DocumentException{
        Document document = DocumentHelper.parseText(xml);
        Element root= document.getRootElement();
        String[] nodes = path.split("\\.");
        String result ="";
        for(int i=1;i<nodes.length;i++){
            if(i==nodes.length-1){
                result= root.elementTextTrim(nodes[i]);
            }  else {
                root = root.element(nodes[i]);
            }
        }
        return result;
    }

    /**
     * @desc 把某节点的所有子节点解析成Map
     * @param path
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseToMapByKey(String xml, String path) throws DocumentException {
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();

        String[] nodes = path.split("\\.");
        for(int i = 1; i < nodes.length; i++){
            if(i < nodes.length){
                root = root.element(nodes[i]);
            }
        }

        Map<String, String> rstMap = new HashMap<>();
        if(root == null) {
            throw new DocumentException("找不到节点");
        } else {
            List<Element> list = root.elements();
            for(Element attribute : list){
                rstMap.put(attribute.getName(), attribute.getText());
            }
        }

        return rstMap;
    }

    /**
     * @desc 把某节点的所有子节点解析成MapList
     * @param path
     * @return
     * @throws Exception
     */
    public static List<HashMap> parseToMapListByKey(String xml, String path) throws DocumentException {
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();

        String[] nodes = path.split("\\.");
        for(int i = 1; i < nodes.length; i++){
            if(i < nodes.length){
                root = root.element(nodes[i]);
            }
        }

        List<HashMap> rstMap = new ArrayList<>();
        if(root == null) {
            throw new DocumentException("找不到节点");
        } else {
            List<Element> list = root.elements();
            for(Element node : list){
                HashMap<String, String> nodeMap = new HashMap<>();
                List<Element> elements = node.elements();
                for(Element element : elements) {
                    nodeMap.put(element.getName(), element.getText());
                }
                if(!nodeMap.isEmpty()) {
                    rstMap.add(nodeMap);
                }
            }
        }

        return rstMap;
    }

    /**
     * java对象转换为xml文件
     *
     * @param obj  java对象
     * @param load java对象.Class
     * @return     xml文件的String
     * @throws     JAXBException
     */
    public static String BeanToXml(Object obj,Class<?> load) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(load);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        return writer.toString();
    }
}
