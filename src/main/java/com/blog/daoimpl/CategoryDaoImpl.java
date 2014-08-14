package com.blog.daoimpl;

import com.blog.entities.Category;
import java.util.List;

/** * @author Armando */
public class CategoryDaoImpl extends GenericDaoImpl<Category>{

    public CategoryDaoImpl() {
        super();
    }
    
    @Override
    public List<Category> findAll(){
        return (List<Category>) EM.createNamedQuery("findAllCategories").getResultList();
    }
    
    @Override
    public Category findByID(int id){
        return (Category) EM.createNamedQuery("findCategoryById").setParameter("P1", id).getSingleResult();
    }
    
    public Category findByName(String name){
        try {
            return (Category) EM.createNamedQuery("findCategoryByName").setParameter("P2",name).getSingleResult();
        } catch (javax.persistence.NoResultException ex){
            return null;
        }    
    }
}
