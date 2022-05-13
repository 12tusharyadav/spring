package com.smartcontact.main.contrller;



import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smartcontact.main.helper.Message;
import com.smartcontact.main.model.Contact;
import com.smartcontact.main.model.User;

import com.smartcontact.main.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	
	
	@Autowired
	private UserRepository userRepository;

	
	
	    //dashboard home
	@GetMapping("/index")
	public String dashBoard(Model model, Principal principal)
	{
		
		
		
		String name = principal.getName();
		System.out.println("*************"+ name);
		User user;
		try {
			user = this.userRepository.getUserByUserName(name);
			System.out.println(user);
			model.addAttribute("user",user);
			return "normal/user_dashboard";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("user not found");
		}
		
		  
		System.out.println(name);
		
		return "normal/user_dashboard";
	}
	
	// open add form handler
	@GetMapping("/add-contact")
	public String openAddContactForm(Model model)
	{
		model.addAttribute("title","add contact form");
		model.addAttribute("contact", new Contact());
		return "normal/add_contactForm";
	}

	// process contact form
	@PostMapping("/process-contact")
	public String prcocessContactForm(@ModelAttribute Contact contact, Principal principal)
	{
		System.out.println("yhan tak aagaya");
	
			System.out.println(contact);
			String name = principal.getName();
			User user = this.userRepository.getUserByUserName(name);
			boolean add = user.getContacts().add(contact);
			contact.setUser(user);
			
			this.userRepository.save(user);
			
			System.out.println("added to databases");
			
		
			return "normal/add_contactForm";
	}
	
//	@GetMapping("/get-contact")
//	public String getContacts(Model model,Principal principal)
//	{
//	model.addAttribute("title","get contact");
//	// CONTACT KI LIST KO LIKANA H
//	
//	String name = principal.getName();
//	User user = this.userRepository.getUserByUserName(name);
//	List<Contact> contacts = this.contactRepository.findContactByUser(user.getId());
//	model.addAttribute("contacts", contacts);
//	System.out.println(contacts);
//	
//	return "normal/get-contact";
//		
//	}
	
}
