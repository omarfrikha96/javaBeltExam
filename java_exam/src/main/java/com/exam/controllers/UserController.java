package com.exam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.exam.models.LoginUser;
import com.exam.models.User;
import com.exam.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	// Add once service is implemented:
	 @Autowired
	 private UserService userService;
	 
		@GetMapping("/")
		public String index(Model model) {
		    model.addAttribute("newUser", new User());
		    model.addAttribute("newLogin", new LoginUser());
		    return "index.jsp";
		}
		
		@PostMapping("/register")
		public String register(@Valid @ModelAttribute("newUser") User newUser, 
				BindingResult result, Model model, HttpSession session) {

		    User user = userService.register(newUser, result);
		     
		    if(result.hasErrors()) {
		        model.addAttribute("newLogin", new LoginUser());
		        return "index.jsp";
		    }			
		    //save the id of the new user in session
		    session.setAttribute("userId", user.getId());
		    return "redirect:/shows";
		}
		
		@PostMapping("/login")
		public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
				BindingResult result, Model model, HttpSession session) {
		     
			User user = userService.login(newLogin, result);
		 
		    if(result.hasErrors() || user==null) {
		        model.addAttribute("newUser", new User());
		        return "index.jsp";
		    }
		    //save the id of the new user in session
		    session.setAttribute("userId", user.getId());
		    return "redirect:/shows";
		}		

		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate(); 
		    return "redirect:/";
		}

}
