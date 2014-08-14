package com.blog.daoimpl;

import com.blog.entities.Attached;
import com.blog.entities.Post;

/** * @author Armando */
public class AttachedDaoImpl extends GenericDaoImpl<Attached>{

    public AttachedDaoImpl() {
        super();
    }

    public Attached findAllByPost(Post p) {
        try {
            return (Attached) EM.createNamedQuery("FindAllAttachedByPost").setParameter("P1", p).getSingleResult();
        } catch (javax.persistence.NoResultException e) {
           return null;
        }
    }
}
