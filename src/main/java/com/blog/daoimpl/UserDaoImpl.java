package com.blog.daoimpl;

import java.util.List;
import com.blog.entities.Status;
import com.blog.entities.User;

/**
 * @author Armando
 */
public class UserDaoImpl extends GenericDaoImpl<User> {

    public UserDaoImpl() {
        super();
    }
    
    @Override
    public List<User> findAll(){
        return (List<User>) EM.createNamedQuery("findAllUsersActive")
                              .setParameter("estatus", Status.A)
                              .getResultList();
    }
    
    public List<User> findAllUsers(){
        return (List<User>) EM.createNamedQuery("findAllUsers").getResultList();     
    }
    
    @Override
    public User findByID(int id){
        return (User) EM.createNamedQuery("findEmpById").setParameter("P1", id).getSingleResult();
    }
    
    public User findByName(String name){
        try {
            return (User) EM.createNamedQuery("findEmpByName").setParameter("P2", name).getSingleResult();
        } catch (javax.persistence.NoResultException ex) {
            return null;
        }
    }
    
    public boolean authenticate(String name, String pass){
        List list = EM.createNamedQuery("authenticate")
                .setParameter("P3", name)
                .setParameter("P4", pass)
                .setParameter("P5", Status.A)
                .getResultList();
        return !list.isEmpty();
    }
}
