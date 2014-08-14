package com.blog.dao;

import java.util.List;

/* @author Armando */
public interface GenericDao <A>{
    
    public void save(A obj);
    public void update(A obj);
    public void delete(A obj);
    public List<A> findAll();
    public A findByID(int id);
    
}
