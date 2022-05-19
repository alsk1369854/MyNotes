package JavaWeb.myspringmvc;

import JavaWeb.Util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    static Map<String, Object> beanMap = new HashMap<>();

    static {
        InputStream is = null;
        try {
            // 1. 讀取 Java Bean 配置文件
//            is = DispatcherServlet.class.getResourceAsStream("applicationContext.xml");
            is = new FileInputStream(new File("C:\\IdeaProjects\\Java_learn\\JavaWeb\\src\\applicationContext.xml"));
            // 2. 創建 DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // 3. 創建 DocumentBuilder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 4. 創建 Document
            Document doc = db.parse(is);
            // 5. 獲取所有 bean 節點
            NodeList beanNodeList = doc.getElementsByTagName("bean");
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node item = beanNodeList.item(i);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) item;
                    String id = element.getAttribute("id");
                    String classAddress = element.getAttribute("class");
                    Object beanInstance = Class.forName(classAddress).newInstance();
                    beanMap.put(id, beanInstance);
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
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // /index.do  -> index
        // index  -> IndexController (配置xml)
        String servletPath = request.getServletPath().substring(1);
        servletPath = servletPath.substring(0, servletPath.lastIndexOf(".d"));

//        String operate = "index";
        String operate = request.getParameter("operate");
        if (StringUtils.isEmpty(operate)) {
            operate = "index";
        }

        // 1. 獲取 Controller 對象
        Object controller = beanMap.get(servletPath);
        try {
            Method[] methods = controller.getClass().getDeclaredMethods();
            for (Method method : methods) {
                // 2. 調用 Controller 對應的 operate 方法
                if (operate.equals(method.getName())) {
                    method.setAccessible(true);
                    Parameter[] parameters = method.getParameters();
                    Object[] values = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        String parameterName = parameters[i].getName();
                        switch (parameterName) {
                            case "request":
                                values[i] = request;
                                break;
                            case "response":
                                values[i] = response;
                                break;
                            case "session":
                                values[i] = request.getSession();
                                break;
                            default:
                                if ("java.lang.Integer".equals(parameters[i].getParameterizedType().getTypeName())) {
                                    values[i] = 1;
                                    String temp = request.getParameter(parameterName);
                                    if (!StringUtils.isEmpty(temp)) {
                                        values[i] = Integer.parseInt(temp);
                                    }
                                } else {
                                    values[i] = request.getParameter(parameterName);
                                }
                                break;
                        }
                    }
                    String returnValue = (String) method.invoke(controller, values);

                    // 3. View處理
                    if (returnValue.startsWith("redirect:")) {
                        response.sendRedirect(returnValue.substring("redirect:".length()));
                    } else {
                        super.processTemplate(returnValue, request, response);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
