package com.blog.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import static javax.persistence.GenerationType.AUTO;

/** * @author Armando */

@Entity
@Table(name="users")
@NamedQueries({@NamedQuery(name="findAllUsers", query="SELECT a FROM User a order by a.userName"),
               @NamedQuery(name="findAllUsersActive", query="SELECT a FROM User a WHERE a.status = :estatus order by a.userName"),
               @NamedQuery(name="findEmpById",  query="SELECT b FROM User b WHERE b.userId = :P1 order by b.userName"),
               @NamedQuery(name="findEmpByName",query="SELECT c FROM User c WHERE c.userName = :P2"),
               @NamedQuery(name="authenticate", query="SELECT d FROM User d WHERE d.userName = :P3 and d.password = :P4 and d.status= :P5")})
public class User implements Serializable {

    @Id
    @Column(name="user_id", length=6)
    @GeneratedValue(strategy=AUTO)
    private int userId;
    
    @Column(name="user_name", nullable=false, length=25, unique=true)
    private String userName;
    
    @Column(name="password", nullable=false, length=40)
    private String password;
    
    @Column(name="status", length=1)
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @OneToMany(mappedBy="user")
    private List<Post> post;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return userName;
    }
}