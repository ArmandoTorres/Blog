package com.blog.daoimpl;

import com.blog.entities.Subscriber;
import java.util.List;

/** * @author Armando */
public class SubscriberDaoImpl extends GenericDaoImpl<Subscriber> {

    public SubscriberDaoImpl() {
        super();
    }

    @Override
    public Subscriber findByID(int id) {
        try {
            return (Subscriber) EM.createNamedQuery("findSubcriberById")
                                  .setParameter("P1", id)
                                  .getSingleResult();
        } catch (javax.persistence.NoResultException e) {
           return null;
       }
    }

    @Override
    public List<Subscriber> findAll() {
        try {
            return (List<Subscriber>) EM.createNamedQuery("findAllSubcriber")
                                        .getResultList();
        } catch (javax.persistence.NoResultException e) {
           return null;
       }
    }
    
    public Subscriber findByEmail(String email){
       try {
            return (Subscriber) EM.createNamedQuery("findSubcriberByEmail")
                                        .setParameter("P2", email)
                                        .getSingleResult();
       } catch (javax.persistence.NoResultException e) {
           return null;
       }
    }
}
