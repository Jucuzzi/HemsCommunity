package com.telek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="t_user")
public class UserEntity  {
	@Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    @Column(name = "id", nullable = false) 
	public int id;
	@Column(name = "name")
	public String name;
	@Column(name = "password")
	public String password;
	@Column(name = "birthday")
	public String birthday;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public UserEntity(String name, String password, String birthday) {
		super();
		this.name = name;
		this.password = password;
		this.birthday = birthday;
	}
	public UserEntity() {
		super();
	}
	@Override
	public String toString() {
		return "UserEntity [name=" + name + ", password=" + password + ", birthday=" + birthday + "]";
	}
	
}
