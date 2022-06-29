package com.amanTech.registration.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "UserDetail")
public class UserDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String mobNo;
	private String password;
	private Boolean isActive;
	@CreationTimestamp
	private LocalDateTime createdAt;
	private int invalidAttemps;
	
	@Transient
	private String token;
	
	public UserDetail() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDetail(String firstName, String lastName, String email, String mobNo, String password,
			boolean isActive, int invalidAttemps) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobNo = mobNo;
		this.password = password;
		this.isActive = isActive;
		this.invalidAttemps=invalidAttemps;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInvalidAttemps() {
		return invalidAttemps;
	}

	public void setInvalidAttemps(int invalidAttemps) {
		this.invalidAttemps = invalidAttemps;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean isActive() {
		return isActive;
	}

	public UserDetail setActive(Boolean isActive) {
		this.isActive = isActive;
		return this;
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

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "userDetail [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", mobNo=" + mobNo
				+ ", password=" + password + "]";
	}

}
