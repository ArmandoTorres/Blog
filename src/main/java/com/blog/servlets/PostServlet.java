package com.blog.servlets;

import com.blog.daoimpl.AttachedDaoImpl;
import com.blog.daoimpl.CommentDaoImpl;
import com.blog.daoimpl.DaoImplSupplier;
import com.blog.daoimpl.PostDaoImpl;
import com.blog.entities.Attached;
import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.util.EmailSender;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Armando
 */
public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PostDaoImpl impl      = (PostDaoImpl) DaoImplSupplier.getInstance("post");
        CommentDaoImpl cImpl  = (CommentDaoImpl) DaoImplSupplier.getInstance("comment");
        AttachedDaoImpl aImpl = (AttachedDaoImpl) DaoImplSupplier.getInstance("attached");
        Post p = impl.findByID(Integer.parseInt(request.getParameter("postId")));
        Attached at = aImpl.findAllByPost(p);
        request.setAttribute("attach", at);
        request.setAttribute("PostData",p);
        request.setAttribute("commentList",cImpl.findAllbyPost(p));
        request.getRequestDispatcher("pages/post.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentDaoImpl impl = (CommentDaoImpl) DaoImplSupplier.getInstance("comment");
        PostDaoImpl postImpl = (PostDaoImpl) DaoImplSupplier.getInstance("post");
        Post post = postImpl.findByID(Integer.parseInt(req.getParameter("actualPost")));
        Comment comment = new Comment();
        boolean flag = true;
        
        if (req.getParameter("emailTB") != null && req.getParameter("emailTB").length() > 0) {
            if (EmailSender.validateEmail(req.getParameter("emailTB"))){
                comment.setEmail(req.getParameter("emailTB"));
            } else {
                req.setAttribute("error", "Enter a valid email address!");
                flag = false;
            }
        }
        
        if (flag) {
            comment.setContent(req.getParameter("commentTB"));
            comment.setName(req.getParameter("nameTB"));
            comment.setPost(post);
            impl.save(comment);
        }
        
        req.setAttribute("PostData",post);
        req.setAttribute("commentList", impl.findAllbyPost(post));
        req.getRequestDispatcher("pages/post.jsp").forward(req, resp);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
