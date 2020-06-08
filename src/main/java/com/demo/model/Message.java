package com.demo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="MESSAGES")
public class Message {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String uuid;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name="IMAGE")
    private String image;
    
    @Column(name="IMAGE")
    public String getImage() {
		return image;
	}

    @Column(name="IMAGE")
	public void setImage(String image) {
		this.image = image;
	}

	public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    @Column(name = "message")
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    @Column(name = "created_at")
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    
    @Column(name = "updated_at")
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
