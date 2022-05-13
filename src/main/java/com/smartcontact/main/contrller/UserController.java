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
import com.smartcontact.main.repository.ContactRepository;
import com.smartcontact.main.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ContactRepository contactRepository;

	
	// method adding data for response
	@ModelAttribute
	public void addCommonData(Model model ,Principal principal)
	{
		
		String name = principal.getName();
			User user;
		try {
			user = this.userRepository.getUserByUserName(name);
			model.addAttribute("user",user);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("user not found");
		}
		
		  
		//System.out.println(name);
		
	}
	
	
	    //dashboard home
	@GetMapping("/index")
	public String dashBoard(Model model, Principal principal)
	{
		
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
	public String prcocessContactForm(@ModelAttribute Contact contact,@RequestParam("profileImage") MultipartFile file, Principal principal)
	{
		System.out.println("yhan tak aagaya");
	
			System.out.println(contact);
		try {
			String name = principal.getName();
			User user = this.userRepository.getUserByUserName(name);
			if(file.isEmpty())
			{
				System.out.println("file is empty(image)");
			}
			else
			{
				// image file upload in the folder and databvse
				
				contact.setImage(file.getOriginalFilename());// file ka name database m set krna k liye
				 
				File saveFile = new ClassPathResource("static/images").getFile(); // file aka object provide kra dega
				
				
				Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());// path bnana ka liye
				
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);// source say sara bytes uthayega or thumhara destination m copy kr dega
				System.out.println("image is uploaded");
				
			}
			
			if(contact != null) {
				contact.setUser(user);
			boolean add = user.getContacts().add(contact);
		
			}
			
			this.userRepository.save(user);
			
			System.out.println("added to databases");
			
		}catch(Exception e)
		{
			System.out.println("Error" +e.getMessage() );
			e.printStackTrace();
		
		}
			return "normal/add_contactForm";
	}
	
	@GetMapping("/get-contact")
	public String getContacts(Model model,Principal principal)
{
	model.addAttribute("title","get contact");
	 //CONTACT KI LIST KO LIKANA H
	
	String name = principal.getName();
	User user = this.userRepository.getUserByUserName(name);
	List<Contact> contacts = this.contactRepository.findContactsByUser(user.getId());
	model.addAttribute("contacts", contacts);
	System.out.println(contacts);
	
	return "normal/get-contact";
	

	}
	
}
