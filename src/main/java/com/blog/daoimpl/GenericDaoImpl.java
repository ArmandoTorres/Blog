package com.blog.daoimpl;

import com.blog.dao.GenericDao;
import com.blog.util.Connexion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/* @author Armando */

public abstract class GenericDaoImpl<A> implements GenericDao<A> {

    EntityManagerFactory EMF;
    EntityManager        EM;
    EntityTransaction    ET;

    public GenericDaoImpl() {
        EMF = Connexion.getPersistUnit();
        EM  = EMF.createEntityManager();
        ET  = EM.getTransaction();
    }
    
    @Override
    public void save(A obj) {
        ET.begin();
        EM.persist(obj);
        ET.commit();
    }

    @Override
    public void update(A obj) {
        ET.begin();
        EM.merge(obj);
        ET.commit();
    }

    @Override
    public void delete(A obj) {
        ET.begin();
        EM.remove(EM.merge(obj));
        ET.commit();
    }

    @Override
    public List<A> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public A findByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
