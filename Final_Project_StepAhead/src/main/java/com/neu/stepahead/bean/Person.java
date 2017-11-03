package com.neu.stepahead.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tblPerson")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "personId", nullable = false, unique = true)
	private long personId;

	@NotEmpty(message = "Please enter First Name.")
	@Column(name = "firstName", nullable = false)
	private String firstName;

	@NotEmpty(message = "Please enter Last Name.")
	@Column(name = "lastName", nullable = false)
	private String lastName;

	@NotEmpty(message = "Please select Gender.")
	@Column(name = "gender", nullable = false)
	private String gender = "M";

	@NotEmpty(message = "Please enter Contact.")
	@Size(min = 10, max = 10, message = "Contact must be of 10 digits only.")
	@Column(name = "contact")
	private String contact;

	@NotEmpty(message = "Please enter Email Id.")
	@Email(message = "Please enter valid Email Id.")
	@Column(name = "emailId")
	private String emailId;

	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
	private Address address;

	@Column(name = "isVerified")
	private boolean isVerified;

	@Column(name = "isCompleteProfile")
	private boolean isCompleteProfile;

	public Person() {

	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public boolean isCompleteProfile() {
		return isCompleteProfile;
	}

	public void setCompleteProfile(boolean isCompleteProfile) {
		this.isCompleteProfile = isCompleteProfile;
	}

}
