package JavaWeb.controllers;

import JavaWeb.Util.StringUtils;
import JavaWeb.bean.Customer;
import JavaWeb.dao.CustomerDAOImpl;
import JavaWeb.dao.Util.JDBCUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public class CustomerController {

    private CustomerDAOImpl custDAO = new CustomerDAOImpl();

    protected String update(Integer id, String name, String birth, String email) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            java.util.Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
            custDAO.update(conn, new Customer(id, name, email, new Date(birthDate.getTime())));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn);
        }
        return "redirect:customer.do";
    }

    protected String delete(String id) {
        if (!StringUtils.isEmpty(id)) {
            Connection conn = null;
            try {
                conn = JDBCUtils.getConnection();
                custDAO.deleteById(conn, Integer.parseInt(id));
                return "redirect:customer.do";
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.closeResource(conn);
            }
        }
        return "error";
    }

    protected String add(String name, String email, String birth) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            java.util.Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
            Customer cust = new Customer(0, name, email, new Date(birthDate.getTime()));
            custDAO.insert(conn, cust);
            return "redirect:customer.do";
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn);
        }
        return "error";
    }

    protected String editor(String custId, HttpServletRequest request) {
        if (!StringUtils.isEmpty(custId)) {
            Connection conn = null;
            try {
                conn = JDBCUtils.getConnection();
                Customer cust = custDAO.getCustomerById(conn, Integer.parseInt(custId));
                request.getSession().setAttribute("cust", cust);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                JDBCUtils.closeResource(conn);
            }
            return "editor";
        }
        return "error";
    }

    protected String index(HttpSession session, String search, String searchValue, Integer pageNo) {
        String keyword;

        List<Customer> custList = null;
        int lastPage = 0;
        if (pageNo == null) {
            pageNo = 1;
        }

        if (!StringUtils.isEmpty(search) && "search".equals(search)) {
            keyword = searchValue;
        } else {
            keyword = (String) session.getAttribute("keyword");
            if (keyword == null) {
                keyword = "";
            }
        }

        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            custList = custDAO.getFive(conn, keyword, (pageNo - 1) * 5);
            lastPage = (int) Math.ceil(custDAO.getCount(conn, keyword).doubleValue() / 5.0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn);
        }
        session.setAttribute("custList", custList);
        session.setAttribute("pageNo", pageNo);
        session.setAttribute("lastPage", lastPage);
        session.setAttribute("keyword", keyword);

        return "index";
    }
}
