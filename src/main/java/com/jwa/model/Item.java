package com.jwa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jwa.model.User;

@Entity
@Table(name = "items")
public class Item {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private String name;
	private String descrition;
	private int cost;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
    @JsonBackReference
	private User user;
	
	public Item() {}
	
	public Item(String name, String descrition, int cost) {
		this.name = name;
		this.descrition = descrition;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
