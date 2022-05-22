package JavaWeb.myssm.myspringmvc;

import JavaWeb.myssm.ioc.BeanFactory;
import JavaWeb.myssm.ioc.ClassPathXmlApplicationContext;
import JavaWeb.myssm.util.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.file.DirectoryNotEmptyException;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext servletContext = this.getServletContext();
        BeanFactory beanFactory = (BeanFactory)servletContext.getAttribute("beanFactory");
        if(beanFactory == null){
            throw new RuntimeException("獲取Application.xml 錯誤");
        }
        this.beanFactory = beanFactory;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request.setCharacterEncoding("utf-8");

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
        Object controller = beanFactory.getBean(servletPath);
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
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new DirectoryNotEmptyException("DirectoryNotEmptyException...");
        }
    }
}
