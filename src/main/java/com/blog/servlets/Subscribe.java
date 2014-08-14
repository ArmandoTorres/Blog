package com.blog.servlets;

import com.blog.daoimpl.CategoryDaoImpl;
import com.blog.daoimpl.DaoImplSupplier;
import com.blog.daoimpl.SubscriberCategoriesDaoImpl;
import com.blog.daoimpl.SubscriberDaoImpl;
import com.blog.entities.Subscriber;
import com.blog.entities.SubscriberCategories;
import com.blog.util.EmailSender;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Armando
 */
public class Subscribe extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("pages/subscribe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        SubscriberCategoriesDaoImpl impl = (SubscriberCategoriesDaoImpl) DaoImplSupplier.getInstance("subscriberCategories");
        SubscriberDaoImpl sImpl = (SubscriberDaoImpl) DaoImplSupplier.getInstance("subscriber");
        CategoryDaoImpl cImpl   = (CategoryDaoImpl) DaoImplSupplier.getInstance("category");
        String[] checkbox = request.getParameterValues("optionsCat");
        
        if (sImpl.findByEmail(request.getParameter("emailTB")) == null){
            if (EmailSender.validateEmail(request.getParameter("emailTB"))) {
                if (checkbox != null){
                    Subscriber s = new Subscriber();
                    s.setName(request.getParameter("nameTB"));
                    s.setEmail(request.getParameter("emailTB"));
                    sImpl.save(s);
                    s = sImpl.findByEmail(s.getEmail());
                    for (int i = 0; i<checkbox.length;++i) {
                        SubscriberCategories sc = new SubscriberCategories();
                        sc.setSubcriber(s);
                        sc.setCategory(cImpl.findByID(Integer.parseInt(checkbox[i].toString())));
                        impl.save(sc);
                    }
                    com.blog.util.EmailSender.sendEmail(s.getEmail(), "Congrats", "You have been subscribed to Programmer Web Blog, from now on you are going to receive emails with theme of your interesting!\n" +
                                                                        "\n" +
                                                                        "Thank for your subscription!");
                    request.setAttribute("success", "Congrat, you subcription have been commited successfully! Check your inbox!");
                } else {
                    request.setAttribute("error", "You must select at least one category!");
                }
            }else {
                request.setAttribute("error", "You must enter a valid email address!");
            }    
        } else {
            request.setAttribute("error", "This email is already registed!");
        }   
        request.getRequestDispatcher("pages/subscribe.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}