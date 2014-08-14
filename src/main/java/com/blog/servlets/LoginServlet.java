package com.blog.servlets;

import com.blog.daoimpl.CategoryDaoImpl;
import com.blog.daoimpl.DaoImplSupplier;
import com.blog.daoimpl.PostDaoImpl;
import com.blog.daoimpl.UserDaoImpl;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Armando
 */
public class LoginServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDaoImpl impl = (UserDaoImpl) DaoImplSupplier.getInstance("user");
        HttpSession hs   = request.getSession();
         try {
            if (impl.authenticate(request.getParameter("userName"), request.getParameter("pass"))){
                hs.setAttribute("AdminUser", impl.findByName(request.getParameter("userName")));
                ServletContext ctx = getServletContext();
                CategoryDaoImpl catImpl = (CategoryDaoImpl) DaoImplSupplier.getInstance("category");
                PostDaoImpl    postImpl = (PostDaoImpl) DaoImplSupplier.getInstance("post");
                ctx.setAttribute("categoryList", catImpl.findAll());
                ctx.setAttribute("postList", postImpl.findAll());
                request.getRequestDispatcher("pages/home.jsp").forward(request, response);
            } else{
               request.setAttribute("mensaje", "The username or password entered are invalid.");
               request.getRequestDispatcher("pages/loginError.jsp").forward(request, response);
            }
        } catch (NullPointerException e){
            request.setAttribute("mensaje", "The username "+request.getParameter("user")+" is not register.");
            request.getRequestDispatcher("pages/loginError.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("param1") != null){
            HttpSession hs = req.getSession();
            hs.invalidate();
            ServletContext ctx = getServletContext();
            ctx.setAttribute("categoryList", ((CategoryDaoImpl)DaoImplSupplier.getInstance("category")).findAll());
            ctx.setAttribute("postList", ((PostDaoImpl)DaoImplSupplier.getInstance("post")).findAll());
            req.getRequestDispatcher("pages/home.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("pages/login.jsp").forward(req, resp);
        }    
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet encargado del login.";
    }// </editor-fold>
}
