package com.blog.servlets;

import com.blog.daoimpl.*;
import com.blog.entities.Category;
import com.blog.entities.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Armando
 */
public class UpdateCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        CategoryDaoImpl cimpl = (CategoryDaoImpl) DaoImplSupplier.getInstance("category");
        PostDaoImpl postImpl = (PostDaoImpl) DaoImplSupplier.getInstance("post");
        CommentDaoImpl commentImpl = (CommentDaoImpl) DaoImplSupplier.getInstance("comment");
        AttachedDaoImpl attachedImpl = (AttachedDaoImpl) DaoImplSupplier.getInstance("attached");
        
        String page = request.getParameter("pageInfomation");
        String ind  = request.getParameter("actions");
        
        if (page == null) {
            if (request.getSession().getAttribute("AdminUser") == null){
                request.getRequestDispatcher("pages/login.jsp").forward(request, response);
            } else {
                request.setAttribute("categoryList", cimpl.findAll());
                request.getRequestDispatcher("pages/AllCategoryTable.jsp").forward(request, response);
            }
        } else if (page.equals("AllCatTable") && ind.equals("Edit")) {
            request.setAttribute("category", cimpl.findByID(Integer.parseInt(request.getParameter("catId"))));
            request.getRequestDispatcher("pages/updateCategory.jsp").forward(request, response);
        } else if (page.equals("AllCatTable") && ind.equals("Delete")){
            Category cat = cimpl.findByID(Integer.parseInt(request.getParameter("catId")));
            List<Post> postList = postImpl.FindAllPostByCategory(cat);
            if (postList != null){
                for (Post p : postList){
                    Attached attached = attachedImpl.findAllByPost(p);
                    if (attached != null){
                        attachedImpl.delete(attached);
                    }
                    List<Comment> commentList = commentImpl.findAllbyPost(p);
                    if(commentList != null){
                        for(Comment c : commentList){
                            commentImpl.delete(c);
                        }
                    }
                    postImpl.delete(p);
                }
            }
            cimpl.delete(cat);
            request.setAttribute("categoryList", cimpl.findAll());
            request.getRequestDispatcher("pages/AllCategoryTable.jsp").forward(request, response);
        }   
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDaoImpl cimpl = (CategoryDaoImpl) DaoImplSupplier.getInstance("category");       
        if (request.getParameter("nameTB") != null && request.getParameter("descriptionTB") != null){
            Category c = cimpl.findByID(Integer.parseInt(request.getParameter("hiddenId")));
            c.setName(request.getParameter("nameTB"));
            c.setDescription(request.getParameter("descriptionTB"));
            cimpl.update(c);
            ServletContext ctx = getServletContext();
            ctx.setAttribute("categoryList", cimpl.findAll());
            request.setAttribute("message", "The chages have been applied successfully!");
        } else {
            request.setAttribute("error", "You must indicate the name and description of the category!");
        }
        request.getRequestDispatcher("pages/updateCategory.jsp").forward(request, response);
    }
}