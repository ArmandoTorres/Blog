package com.blog.daoimpl;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import java.util.List;

/** * @author Armando */
public class CommentDaoImpl extends GenericDaoImpl<Comment>{

    public CommentDaoImpl() {
        super();
    }

    public List<Comment> findAllbyPost (Post id){
        return (List<Comment>) EM.createNamedQuery("FindAllCommentByPost").setParameter("P1", id).getResultList();
    }
}
