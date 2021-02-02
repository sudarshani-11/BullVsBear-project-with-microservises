package com.bullvsbear.dbservice.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="user_id")
	private long id;
	
	@Column(name = "username",length = 15,nullable = false,unique = true)
	private String userName;

	@Column(name="first_name",length=20,nullable = false,unique=false)
	private String firstName;
	
	@Column(name="last_name",length=20,nullable = false,unique=false)
	private String lastName;
		
	@Column(name="email",length=50,nullable = false,unique = true)
	private String email;
	
	@Column(name="date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;
	
	@Column(name="password",length=80,nullable = false,unique = false )
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.LAZY)
	private List<UserQuote> userQuotes;
	
	public User() {
	}
	
	public User(String userName, String firstName, String lastName, String email, String password) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public long getUserId() {
		return id;
	}

	public void setUserId(long userId) {
		this.id = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public List<UserQuote> getUserQuote() {
		return userQuotes;
	}

	public void setUserQuote(List<UserQuote> userQuotes) {
		this.userQuotes = userQuotes;
	}
	
	
	
}
