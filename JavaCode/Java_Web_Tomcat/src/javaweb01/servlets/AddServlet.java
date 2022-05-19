package javaweb01.servlets;

import JavaWeb.bean.Customer;
import JavaWeb.dao.CustomerDAOImpl;
import JavaWeb.dao.Util.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddServlet extends HttpServlet {

    public AddServlet(){
        System.out.println("new AddServlet -----------------------------------");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
//        request.getSession(true);
//        request.getSession(false);

//        session.getId();
//        session.isNew();
//        session.getMaxInactiveInterval();
//        session.setMaxInactiveInterval(1800);
//        session.invalidate();

//        session.setAttribute("uname", "Ming");
//        session.getAttribute("Uname");
//        session.removeAttribute("uname");

//        request.getRequestDispatcher("demo2").forward(request,response);
//        response.sendRedirect("demo2");

        request.setCharacterEncoding("utf-8");
        Connection conn = null;
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date birth = sdf.parse(request.getParameter("birth"));

            Customer cust = new Customer(0, name, email, new Date(birth.getTime()));
            System.out.println(cust);
            conn = JDBCUtils.getConnection();
            System.out.println(conn);
            CustomerDAOImpl customerDAO = new CustomerDAOImpl();
            customerDAO.insert(conn, cust);

        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        } finally{
            JDBCUtils.closeResource(conn);
        }
    }
}
