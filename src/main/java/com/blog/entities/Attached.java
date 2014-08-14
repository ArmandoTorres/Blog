package com.blog.entities;

import java.io.Serializable;
import javax.persistence.*;

/** * @author Armando */
@Entity
@Table(name="attached")
@NamedQueries({@NamedQuery(name="FindAllAttachedByPost", query="SELECT a FROM Attached a WHERE a.post = :P1")})
public class Attached implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="attached_id",length=6,nullable=false)
    private int attachedId;
    
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
    
    @Column(name="file_name",nullable=false,length=30)
    private String fileName;
    
    @Column(name="userName",length=25)
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAttachedId() {
        return attachedId;
    }

    public void setAttachedId(int attachedId) {
        this.attachedId = attachedId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
