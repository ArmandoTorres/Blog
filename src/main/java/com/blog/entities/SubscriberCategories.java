package com.blog.entities;

import java.io.Serializable;
import javax.persistence.*;

/** * @author Armando */
@Entity
@Table(name="subscriber_categories")
@NamedQueries({@NamedQuery(name="findAllCategoryBySubscriber",query="SELECT a FROM SubscriberCategories a WHERE a.subcriber = :P1"),
               @NamedQuery(name="findAllSubscriberByCategory",query="SELECT b FROM SubscriberCategories b WHERE b.category  = :P2")})
public class SubscriberCategories implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id",length=6,nullable=false)
    private int id;
    
    @ManyToOne
    @JoinColumn(name="subcriber_id")
    private Subscriber subcriber;
    
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subscriber getSubcriber() {
        return subcriber;
    }

    public void setSubcriber(Subscriber subcriberId) {
        this.subcriber = subcriberId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category categoryId) {
        this.category = categoryId;
    }
}