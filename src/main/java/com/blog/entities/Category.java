package com.blog.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/** * @author Armando  */
@Entity
@Table(name="categories")
@NamedQueries({@NamedQuery(name="findAllCategories", query="SELECT a FROM Category a order by a.name"),
               @NamedQuery(name="findCategoryById", query="SELECT b FROM Category b WHERE b.categoryId = :P1"),
               @NamedQuery(name="findCategoryByName", query="SELECT c FROM Category c WHERE c.name = :P2")})
public class Category implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="category_id",length=6)
    private int categoryId;
    
    @Column(name="name", nullable=false, length=30, unique=true)
    private String name;
    
    @Column(name="description", nullable=false, length=220)
    private String description;
    
    @Column(name="icon", length=30)
    private String icon;
    
    @OneToMany(mappedBy="category")
    private List<Post> post;
    
    @OneToMany(mappedBy="category")
    private List<SubscriberCategories> subscriberCategories;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
