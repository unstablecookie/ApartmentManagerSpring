package org.domainname.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

@Entity
@Table(name = "USERS")
public class User {
	
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
	@SequenceGenerator(name = "users_id_seq",sequenceName="users_id_seq", allocationSize = 1)
	private long id;					//NO SET METHOD
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="user_name",unique=true) //UNIQUE
	private String userName;
	
	public User() {}
	
	public User(String fName,String lName,String uName) {
		this.firstName = fName; 
		this.lastName = lName; 
		this.userName = uName; 
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String fName) {
		this.firstName = fName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lName) {
		this.lastName = lName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String uName) {
		this.userName = uName;
	}
	
	@Override
	public boolean equals(Object obj) {
		User user = (User)obj;
		return (this.id == user.getId());
	}
}
