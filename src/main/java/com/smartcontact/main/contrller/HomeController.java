package com.smartcontact.main.contrller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartcontact.main.helper.Message;
import com.smartcontact.main.model.User;
import com.smartcontact.main.repository.UserRepository;

@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/")
	public String home(Model model)
	{
		model.addAttribute("title", "home smart contact manager");
		return "home";
	}
	
	

	@RequestMapping("/about")
	public String about(Model model)
	{
		model.addAttribute("title", "about smart contact manager");
		return "about";
	}
	

	@RequestMapping("/signUp")
	public String signUp(Model model)
	{
		model.addAttribute("title", "signUp smart contact manager");
		model.addAttribute("user", new User());
		return "signUp";
	}

	@GetMapping("/signin")
	public String customLogin(Model model)
	{
		model.addAttribute("title", "login smart contact manager");
		return "login";
	}
	
	@RequestMapping(value ="/register",method=RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user,BindingResult result1,  @RequestParam(value = "agrrement",defaultValue = "false") boolean agrrement, Model model,HttpSession session)
	{
		
		
		try
	{
			
//		
		if(!agrrement)
		{
			System.out.println("you have not agreed the terms and conditions");
			
			throw new Exception("you have not agreed the terms and conditions");
		}
//		
		
		if(result1.hasErrors())
		{
			System.out.println("error"+result1);
			
			model.addAttribute("user", user);
			return "signUp";
		}
		
//		
		user.setRole("ROLE_USER");
		user.setImageUrl("default.png");
		
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println("AGGREMENT" +agrrement);
		System.out.println("User" +user);
	
		
		this.userRepository.save(user);
		
		model.addAttribute("user", new User());
		
		session.setAttribute("message", new Message("success", "alert-success"));
		return "signUp";
//		
		
		}catch(Exception e)
		{
			e.printStackTrace();
			session.setAttribute("message", new Message("something went wrong", "alert-danger"));
			return "signUp";
		}
		
//	
//		
	
	}
}

