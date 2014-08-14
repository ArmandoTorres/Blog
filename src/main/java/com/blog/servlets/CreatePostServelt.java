package com.blog.servlets;

import com.blog.daoimpl.AttachedDaoImpl;
import com.blog.daoimpl.CategoryDaoImpl;
import com.blog.daoimpl.DaoImplSupplier;
import com.blog.daoimpl.PostDaoImpl;
import com.blog.daoimpl.SubscriberCategoriesDaoImpl;
import com.blog.entities.Attached;
import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.SubscriberCategories;
import com.blog.entities.User;
import com.blog.util.EmailSender;
import com.blog.util.UploadFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author Armando
 */
public class CreatePostServelt extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "images";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        AttachedDaoImpl  attImpl = (AttachedDaoImpl) DaoImplSupplier.getInstance("attached");
        PostDaoImpl     postImpl = (PostDaoImpl) DaoImplSupplier.getInstance("post");
        CategoryDaoImpl catImpl  = (CategoryDaoImpl) DaoImplSupplier.getInstance("category");
        User us        = (User) request.getSession().getAttribute("AdminUser");
        Post post      = new Post();
        Category cat   = new Category();
        String fileName = "";

        //Getting value from page
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items){
                if (item.isFormField()){
                    if(item.getFieldName().equals("selectCat")){
                        cat = catImpl.findByID(Integer.parseInt(item.getString()));   
                    } else if (item.getFieldName().equals("titleTB")) {
                        post.setTitle(item.getString());
                    } else if (item.getFieldName().equals("contentTB")) {
                        post.setContent(item.getString()); 
                    } 
                } else {
                    if (!item.getName().isEmpty()){
                        fileName = UploadFile.uploadFile(request, getServletContext().getRealPath("")+
                                                        File.separator + UPLOAD_DIRECTORY + 
                                                        File.separator + us.getUserName(), 
                                                        item);
                    }
                }
            }
            
        } catch (FileUploadException ex) {
            Logger.getLogger(CreatePostServelt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        post.setUser(us);
        post.setCategory(cat);
        postImpl.save(post);
        
        if ((!fileName.isEmpty()) && fileName.length() > 0){
            Attached attached = new Attached();
            attached.setFileName(fileName);
            attached.setPost(post);
            attached.setUserName(us.getUserName());
            attImpl.save(attached);
        }    
        sendEmailForAllSubscriber(cat);
        request.setAttribute("message", "Your post have been created successfully!");
        request.getRequestDispatcher("pages/createPost.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hs = request.getSession();
        if (hs.getAttribute("AdminUser") == null){
            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("pages/createPost.jsp").forward(request, response);
        }
    }
    
    private void sendEmailForAllSubscriber(Category cat){
        SubscriberCategoriesDaoImpl impl = (SubscriberCategoriesDaoImpl) DaoImplSupplier.getInstance("subscriberCategories");
        List<SubscriberCategories> sc = impl.findAllSubscriberByCategory(cat);
        String[] emails = new String[sc.size()];
        int cont = 0;
        
        if (sc != null){
            for (SubscriberCategories s : sc){
                emails[cont]= s.getSubcriber().getEmail();
                cont++;
            }
            EmailSender.sendEmail(emails, "New Post was Added!", "Check new post under category of "+cat.getName());
        }
    }
}
