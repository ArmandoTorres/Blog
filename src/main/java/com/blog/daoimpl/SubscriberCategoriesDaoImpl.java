package com.blog.daoimpl;

import com.blog.entities.Category;
import com.blog.entities.SubscriberCategories;
import java.util.List;

/**
 * @author Armando
 */
public class SubscriberCategoriesDaoImpl extends GenericDaoImpl<SubscriberCategories> {
    
    public List<SubscriberCategories> findAllBySubscriber(int id){
        return (List<SubscriberCategories>)EM.createNamedQuery("findAllCategoryBySubscriber")
                                             .setParameter("P1", id)
                                             .getResultList();
    }
    
    public List<SubscriberCategories> findAllSubscriberByCategory(Category cat){
        return (List<SubscriberCategories>)EM.createNamedQuery("findAllSubscriberByCategory")
                                             .setParameter("P2", cat)
                                             .getResultList();
    }
       
}
