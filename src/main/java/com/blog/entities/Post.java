package com.blog.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/** * @author Armando */
@Entity
@Table(name="post")
@NamedQueries({@NamedQuery(name="FindAllPost",query="SELECT a FROM Post a"),
               @NamedQuery(name="FindAllPostByCategory",query="SELECT b FROM Post b WHERE b.category = :P1"), 
               @NamedQuery(name="FindPostById",query="SELECT c FROM Post c WHERE c.postId = :P2"),
               @NamedQuery(name="FindPostByUser",query="SELECT d FROM Post d WHERE d.user = :P3")
              })
public class Post implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="post_id",length=6,nullable=false)
    private int postId;
    
    @Column(name="title",length=30,nullable=false)
    private String title;
    
    @Column(name="content",length=2000)
    private String content;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    
    @OneToMany(mappedBy="post")
    private List<Attached> attached;
    
    @OneToMany(mappedBy="post")
    private List<Comment> comment;

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public List<Attached> getAttached() {
        return attached;
    }

    public void setAttached(List<Attached> attached) {
        this.attached = attached;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title.trim();
    }

    public void setTitle(String title) {
        this.title = title.trim();
    }

    public String getContent() {
        return content.trim();
    }

    public void setContent(String content) {
        this.content = content.trim();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
}
