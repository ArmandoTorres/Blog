package com.blog.daoimpl;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import java.util.List;

/** * @author Armando */
public class PostDaoImpl extends GenericDaoImpl<Post>{

    public PostDaoImpl() {
        super();
    }

    @Override
    public Post findByID(int id) {
        return (Post) EM.createNamedQuery("FindPostById").setParameter("P2", id).getSingleResult();
    }

    @Override
    public List<Post> findAll() {
        return (List<Post>) EM.createNamedQuery("FindAllPost").getResultList();
    }
    
    public List<Post> FindAllPostByCategory(Category cat) {
        return (List<Post>) EM.createNamedQuery("FindAllPostByCategory").setParameter("P1", cat).getResultList();
    }
    
    public List<Post> FindPostByUser(User usr){
        return (List<Post>) EM.createNamedQuery("FindPostByUser").setParameter("P3", usr).getResultList();
    }
}