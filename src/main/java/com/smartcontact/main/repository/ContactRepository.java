package com.smartcontact.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smartcontact.main.model.Contact;



public interface ContactRepository extends JpaRepository<Contact, Integer> {

	
	@Query("from Contact as d  where d.user.id=:userId")
	public List<Contact> findContactsByUser(@Param("userID") int UserId);
}
