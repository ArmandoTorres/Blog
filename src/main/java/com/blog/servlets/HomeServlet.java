package com.blog.servlets;

import com.blog.daoimpl.CategoryDaoImpl;
import com.blog.daoimpl.DaoImplSupplier;
import com.blog.daoimpl.PostDaoImpl;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Armando
 */
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Se consigue el ServeltContext el cual es visto por la aplicacion completa
        //y se crean dos objetos globales para que siempre esten en toda la aplicacion.
        ServletContext ctx = getServletContext();
        CategoryDaoImpl catImpl = (CategoryDaoImpl) DaoImplSupplier.getInstance("category");
        PostDaoImpl    postImpl = (PostDaoImpl) DaoImplSupplier.getInstance("post");
        ctx.setAttribute("categoryList", catImpl.findAll());
        ctx.setAttribute("postList", postImpl.findAll());
        request.getRequestDispatcher("pages/home.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Home page servlet";
    }// </editor-fold>

}
