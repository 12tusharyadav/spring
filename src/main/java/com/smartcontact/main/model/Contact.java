package com.smartcontact.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer cid;
	private String name;
	private String nickname;
	@Column(length = 1200)
	private String description;

	private boolean enabled;
	private String work;
	
    private String image;
	
	@Column(unique = true)
	private String email;
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(Integer cid, String name, String nickname, String description, boolean enabled, String work,
			String image, String email, Integer phoneno, User user) {
		super();
		this.cid = cid;
		this.name = name;
		this.nickname = nickname;
		this.description = description;
		this.enabled = enabled;
		this.work = work;
		this.image = image;
		this.email = email;
		this.phoneno = phoneno;
		this.user = user;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(Integer phoneno) {
		this.phoneno = phoneno;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private Integer phoneno;
	@ManyToOne
	private User user;
	
	@Override
	public String toString() {
		return "Contact [cid=" + cid + ", name=" + name + ", nickname=" + nickname + ", description=" + description
				+ ", enabled=" + enabled + ", work=" + work + ", image=" + image + ", email=" + email + ", phoneno="
				+ phoneno + ", user=" + user + "]";
	}
	
	

}
