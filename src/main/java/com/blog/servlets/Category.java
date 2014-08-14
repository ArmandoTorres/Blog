package com.blog.servlets;

import com.blog.daoimpl.CategoryDaoImpl;
import com.blog.daoimpl.DaoImplSupplier;
import com.blog.daoimpl.PostDaoImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Armando
 */
public class Category extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PostDaoImpl p = (PostDaoImpl) DaoImplSupplier.getInstance("post");
        CategoryDaoImpl catImpl = (CategoryDaoImpl) DaoImplSupplier.getInstance("category");
        request.setAttribute("PostListByCategory",p.FindAllPostByCategory(catImpl.findByID(Integer.parseInt(request.getParameter("catId")))));
        request.getRequestDispatcher("pages/categories.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servelt encargado de mostrar las categorias.";
    }// </editor-fold>

}
