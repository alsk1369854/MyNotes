package JavaWeb.myssm.filters;

import JavaWeb.myssm.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"*.do"}, initParams = {@WebInitParam(name = "encoding", value = "utf-8")})
public class CharacterEncodingFilter implements Filter {

    private String encoding = "utf-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingStr = filterConfig.getInitParameter("encoding");
        if(StringUtils.isEmpty(encodingStr)){
            encoding = encodingStr;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // set utf-8 encoding
        ((HttpServletRequest)servletRequest).setCharacterEncoding(encoding);
        // 跨域訪問
        ((HttpServletResponse)servletResponse).setContentType("text/html;charset=utf-8");
        ((HttpServletResponse)servletResponse).setHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse)servletResponse).setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE");
        ((HttpServletResponse)servletResponse).setHeader("Access-Control-Max-Age", "3600");
        ((HttpServletResponse)servletResponse).setHeader("Access-Control-Allow-Headers", "*");
        ((HttpServletResponse)servletResponse).setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
