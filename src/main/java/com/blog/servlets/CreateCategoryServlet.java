package com.blog.servlets;

import com.blog.daoimpl.CategoryDaoImpl;
import com.blog.daoimpl.DaoImplSupplier;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.blog.entities.Category;
import javax.servlet.ServletContext;

/** * @author Armando */
public class CreateCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("AdminUser") == null) {
            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
        } else {    
            request.getRequestDispatcher("pages/createCategory.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("nameTB") != null && request.getParameter("descriptionTB") != null){
            CategoryDaoImpl impl = (CategoryDaoImpl) DaoImplSupplier.getInstance("category");
            if (impl.findByName(request.getParameter("nameTB")) == null){
                Category cat = new Category();
                cat.setName(request.getParameter("nameTB"));
                cat.setDescription(request.getParameter("descriptionTB"));
                impl.save(cat);
                ServletContext ctx = getServletContext();
                ctx.setAttribute("categoryList", impl.findAll());
                request.setAttribute("message", "Your category have been created successfully!");
            } else {
                request.setAttribute("error", "There is a category with the same name!");
            }  
        } else {
            request.setAttribute("error", "You must indicate the name and description of the category!");
        }
        request.getRequestDispatcher("pages/createCategory.jsp").forward(request, response);
    }
}
