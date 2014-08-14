package com.blog.servlets;

import com.blog.daoimpl.DaoImplSupplier;
import com.blog.daoimpl.UserDaoImpl;
import com.blog.entities.Status;
import com.blog.entities.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Armando
 */
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDaoImpl impl = (UserDaoImpl) DaoImplSupplier.getInstance("user");
        if (request.getSession().getAttribute("AdminUser") == null){
            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
        } else if (request.getParameter("userId") != null){
            User user = impl.findByID(Integer.parseInt(request.getParameter("userId")));
            if (request.getParameter("option").equals("Disable")){
                user.setStatus(Status.I);
                impl.update(user);
                request.setAttribute("userList", impl.findAllUsers());
                request.getRequestDispatcher("pages/AllUserTable.jsp").forward(request, response);
            } else if (request.getParameter("option").equals("Enabled")){
                user.setStatus(Status.A);
                impl.update(user);
                request.setAttribute("userList", impl.findAllUsers());
                request.getRequestDispatcher("pages/AllUserTable.jsp").forward(request, response);
            } else {
                request.setAttribute("selectedUser", user);
                request.getRequestDispatcher("pages/updateUser.jsp").forward(request, response);
            }  
        } else {
            request.setAttribute("userList", impl.findAllUsers());
            request.getRequestDispatcher("pages/AllUserTable.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDaoImpl impl = (UserDaoImpl) DaoImplSupplier.getInstance("user");
        User usr = impl.findByID(Integer.parseInt(request.getParameter("userId")));
        String pass1 = request.getParameter("passwordTB1");
        String pass2 = request.getParameter("passwordTB2");
        if (pass1 != null && pass2 != null){
            if (pass1.equals(pass2)){
                usr.setPassword(pass1);
                impl.save(usr);
                request.setAttribute("message", "The password was change successfully!");
            } else {
                request.setAttribute("error", "Both passwords must be equals!");
            }
        } else {
            request.setAttribute("error", "You must enter both password!");
        }
        request.setAttribute("selectedUser", usr);
        request.getRequestDispatcher("pages/updateUser.jsp").forward(request, response);
    }
}