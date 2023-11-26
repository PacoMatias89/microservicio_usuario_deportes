package com.pacomolina.tipodeportes.entity;

import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "sports")
@EntityListeners(AuditingEntityListener.class)
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sport")
    private String sport;

    @Column(name = "type")
    private String type;
    
    @Column(name = "user_Id")
    private int userId;

    public Sport() {
    }


    public Sport(int id, 
    		String sport, 
    		String type, 
    		int userId) {
		this.id = id;
		this.sport = sport;
		this.type = type;
		this.userId = userId;
	}


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "Sport [id=" + id + ", sport=" + sport + ", type=" + type + ", userId=" + userId + "]";
	}
    

    
}
