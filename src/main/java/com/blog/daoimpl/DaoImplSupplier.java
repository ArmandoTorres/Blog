package com.blog.daoimpl;

/** * @author Armando */
public class DaoImplSupplier {
    
    private static AttachedDaoImpl attached;
    private static CategoryDaoImpl category;
    private static CommentDaoImpl  comment;
    private static PostDaoImpl     post;
    private static SubscriberCategoriesDaoImpl subscriberCategories;
    private static SubscriberDaoImpl subscriber;
    private static UserDaoImpl       user;
    
    public static GenericDaoImpl getInstance(String instance){
        if (instance.equals("attached")){
            if (attached == null){
                attached = new AttachedDaoImpl();
            } 
            return attached;
        } else if (instance.equals("category")){
            if (category == null){
                category = new CategoryDaoImpl();
            }
            return category;
        } else if (instance.equals("comment")){
            if (comment == null){
                comment = new CommentDaoImpl();
            }
            return comment;
        } else if (instance.equals("post")){
            if (post == null){
                post = new PostDaoImpl();
            }
            return post;
        } else  if (instance.equals("subscriberCategories")){
            if (subscriberCategories == null){
                subscriberCategories = new SubscriberCategoriesDaoImpl();
            }
            return subscriberCategories;
        } else if (instance.equals("subscriber")){
            if (subscriber == null){
                subscriber = new SubscriberDaoImpl();
            }
            return subscriber;
        } else if (instance.equals("user")){
            if (user == null){
                user = new UserDaoImpl();
            }
            return user;
        }
        return null;
    }
    
}
