package com.smartcontact.main.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
public class User {

	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer id;
	@NotBlank(message="name can not blank")
	@Size(min=3,max=15,message="user name must be 6 to 15 character")
	private String name;
	@Pattern(regexp ="^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",message ="At least 8 chars, \r\n"
			+ "\r\n"
			+ "Contains at least one digit,\r\n"
			+ "\r\n"
			+ "Contains at least one lower alpha char and one upper alpha char,\r\n"
			+ "\r\n"
			+ "Contains at least one char within a set of special chars (@#%$^ etc.),\r\n"
			+ "\r\n"
			+ "Does not contain space, tab, etc.")
	private String password;
	@Column(length = 1000)
	private String about;
	private String imageUrl;
	private boolean enabled;
	private String role;
	@Column(unique = true)
	@Pattern(regexp ="^(.+)@(\\S+)$" , message = "Invalid Email:only Email valid demo(username@domain.com)")
	private String email;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy = "user")
	private List<Contact> contacts = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", about=" + about + ", imageUrl="
				+ imageUrl + ", enabled=" + enabled + ", role=" + role + ", email=" + email + ", contacts=" + contacts
				+ "]";
	}

	public User(Integer id,
			@NotBlank(message = "name can not blank") @Size(min = 6, max = 15, message = "user name must be 6 to 15 character") String name,
			@Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "At least 8 chars, \r\n\r\nContains at least one digit,\r\n\r\nContains at least one lower alpha char and one upper alpha char,\r\n\r\nContains at least one char within a set of special chars (@#%$^ etc.),\r\n\r\nDoes not contain space, tab, etc.") String password,
			String about, String imageUrl, boolean enabled, String role,
			@Pattern(regexp = "^(.+)@(\\S+)$", message = "Invalid Email:only Email valid demo(username@domain.com)") String email,
			List<Contact> contacts) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.about = about;
		this.imageUrl = imageUrl;
		this.enabled = enabled;
		this.role = role;
		this.email = email;
		this.contacts = contacts;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
