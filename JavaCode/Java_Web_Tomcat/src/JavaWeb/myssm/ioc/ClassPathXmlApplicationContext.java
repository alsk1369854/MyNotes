package JavaWeb.myssm.ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlApplicationContext implements BeanFactory {

    private  Map<String, Object> beanMap = new HashMap<>();
    private String path = "config/applicationContext.xml";

    public ClassPathXmlApplicationContext(){
        this("config/applicationContext.xml");
    }

    public ClassPathXmlApplicationContext(String path) {
        InputStream is = null;
        try {
            // 1. 讀取 Java Bean 配置文件
            is = new FileInputStream(new File(path));
            // 2. 創建 DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // 3. 創建 DocumentBuilder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 4. 創建 Document
            Document doc = db.parse(is);
            // 5. 獲取所有 bean 節點，並保存至 beanMap
            NodeList beanNodeList = doc.getElementsByTagName("bean");
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node item = beanNodeList.item(i);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) item;
                    String id = element.getAttribute("id");
                    String classAddress = element.getAttribute("class");
                    // 創建 bean 的實例
                    Object beanInstance = Class.forName(classAddress).newInstance();
                    // 將 bean 保存到 beanMap
                    beanMap.put(id, beanInstance);
                    // 到目前為止 bean之間的依賴關係還沒有設置
                }
            }
            // 6. 組裝 bean 之間的依賴關係
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node item = beanNodeList.item(i);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) item;
                    String id = element.getAttribute("id");
                    Object currentBean = beanMap.get(id);
                    NodeList childNodes = element.getChildNodes();
                    for(int j=0; j< childNodes.getLength(); j++){
                        Node childItem = childNodes.item(j);
                        if(childItem.getNodeType() == Node.ELEMENT_NODE){
                            Element childElement = (Element) childItem;
                            String name = childElement.getAttribute("name");
                            String ref = childElement.getAttribute("ref");
                            Object refBean = beanMap.get(ref);
                            Field currentBeanField = currentBean.getClass().getDeclaredField(name);
                            currentBeanField.setAccessible(true);
                            currentBeanField.set(currentBean, refBean);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
