package com.blog.entities;

import java.io.Serializable;
import javax.persistence.*;

/** * @author Armando */
@Entity
@Table(name="comments")
@NamedQueries({@NamedQuery(name="FindAllCommentByPost",query="SELECT a FROM Comment a WHERE a.post = :P1")})
public class Comment implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="comment_id",length=6,nullable=false)
    private int commentId;
    
    @Column(name="name",nullable=false,length=25)
    private String name;
    
    @Column(name="email",length=40)
    private String email;
    
    @Column(name="content",nullable=false,length=2000)
    private String content;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
