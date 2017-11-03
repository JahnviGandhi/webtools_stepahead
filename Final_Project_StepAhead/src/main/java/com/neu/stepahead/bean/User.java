package com.neu.stepahead.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tblUser")
@PrimaryKeyJoinColumn(name = "personId")
public class User extends Person {
	/*
	 * @GeneratedValue(strategy = GenerationType.IDENTITY)
	 * 
	 * @Column(name = "userId", nullable = false, unique = true) private long
	 * userId;
	 */

	@NotEmpty(message = "Please enter User Name.")
	@Column(name = "userName")
	private String userName;

	@NotEmpty(message = "Please enter Password.")
	@Size(min = 8, max = 15, message = "Password must be between 8 to 15 characters.")
	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role;

	@Column(name = "isActive")
	private boolean isActive;

	@Column(name = "isVerified")
	private boolean isVerified;

	public User() {

	}
	/*
	 * public long getUserId() { return userId; }
	 * 
	 * public void setUserId(long userId) { this.userId = userId; }
	 */

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
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
}
