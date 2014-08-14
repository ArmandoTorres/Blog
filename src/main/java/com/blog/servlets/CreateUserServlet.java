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
public class CreateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("AdminUser") == null){
            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("pages/createUser.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("userNameTB")!= null && request.getParameter("passwordTB") != null){
            UserDaoImpl userImpl = (UserDaoImpl) DaoImplSupplier.getInstance("user");
            if (userImpl.findByName(request.getParameter("userNameTB")) == null) {
                User user = new User();
                user.setUserName(request.getParameter("userNameTB"));
                user.setPassword(request.getParameter("passwordTB"));
                user.setStatus(Status.A);
                userImpl.save(user);
                request.setAttribute("message", "The user was created successfully.");
            } else {
                request.setAttribute("error", "There is another user with the same name.");
            }
        } else {
            request.setAttribute("error", "You must enter the name and password for the user!");
        }
        request.getRequestDispatcher("pages/createUser.jsp").forward(request, response);
    }
}