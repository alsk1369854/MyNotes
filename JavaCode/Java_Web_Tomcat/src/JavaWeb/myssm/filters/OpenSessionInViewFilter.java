package JavaWeb.myssm.filters;

import JavaWeb.myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            TransactionManager.beginTrans();
//            System.out.println("事務執行");
            filterChain.doFilter(servletRequest, servletResponse);
            TransactionManager.commit();
//            System.out.println("事務結束");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                TransactionManager.rollback();
//                System.out.println("事務回滾");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
