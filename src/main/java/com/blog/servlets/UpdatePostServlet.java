package com.blog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.blog.daoimpl.*;
import com.blog.entities.*;
import java.util.List;

/**
 * @author Armando
 */
public class UpdatePostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PostDaoImpl postImpl = (PostDaoImpl) DaoImplSupplier.getInstance("post");
        CommentDaoImpl commentImpl = (CommentDaoImpl) DaoImplSupplier.getInstance("comment");
        AttachedDaoImpl attachedImpl = (AttachedDaoImpl) DaoImplSupplier.getInstance("attached");
        String page = request.getParameter("pageInfomation");
        String ind  = request.getParameter("actions");

        if (page == null){
            request.setAttribute("postList", postImpl.findAll());
            request.getRequestDispatcher("pages/AllPostTable.jsp").forward(request, response);
        } else if (page.equals("AllPostTable") && ind.equals("Edit")){
            request.setAttribute("post", postImpl.findByID(Integer.parseInt(request.getParameter("postId"))));
            request.getRequestDispatcher("pages/updatePost.jsp").forward(request, response);
        } else if (page.equals("AllPostTable") && ind.equals("Delete")){
            Post p = postImpl.findByID(Integer.parseInt(request.getParameter("postId")));
            Attached at = attachedImpl.findAllByPost(p);
            if (at != null){
                attachedImpl.delete(at);
            }
            List<Comment> ct  = commentImpl.findAllbyPost(p);
            if (ct != null) {
                for (Comment c : ct){
                    commentImpl.delete(c);
                }
            }    
            postImpl.delete(p);
            request.setAttribute("postList", postImpl.findAll());
            request.getRequestDispatcher("pages/AllPostTable.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PostDaoImpl     postImpl = (PostDaoImpl) DaoImplSupplier.getInstance("post");
        CategoryDaoImpl catImpl  = (CategoryDaoImpl) DaoImplSupplier.getInstance("category");
        Post p = postImpl.findByID(Integer.parseInt(request.getParameter("hiddenId")));
        p.setTitle(request.getParameter("titleTB"));
        p.setContent(request.getParameter("contentTB"));
        p.setCategory(catImpl.findByID(Integer.parseInt(request.getParameter("selectCat"))));
        postImpl.update(p);
        request.setAttribute("postList", postImpl.findAll());
        request.getRequestDispatcher("pages/AllPostTable.jsp").forward(request, response);
    }
}