package com.blog.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/** * @author Armando */
@Entity
@Table(name="subscribers")
@NamedQueries({@NamedQuery(name="findAllSubcriber",query="SELECT a FROM Subscriber a"),
               @NamedQuery(name="findSubcriberById",query="SELECT b FROM Subscriber b WHERE b.subscriberId = :P1"),
               @NamedQuery(name="findSubcriberByEmail",query="SELECT c FROM Subscriber c WHERE c.email = :P2")
              })
public class Subscriber implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="subcriber_id",length=6,nullable=false)
    private int subscriberId;
    
    @Column(name="email",nullable=false,length=40)
    private String email;
    
    @Column(name="name",nullable=false,length=25)
    private String name;
    
    @OneToMany(mappedBy="subcriber")
    private List<SubscriberCategories> subscriberCategories;

    public List<SubscriberCategories> getSubscriberCategories() {
        return subscriberCategories;
    }

    public void setSubscriberCategories(List<SubscriberCategories> subscriberCategories) {
        this.subscriberCategories = subscriberCategories;
    }

    public int getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
       
}