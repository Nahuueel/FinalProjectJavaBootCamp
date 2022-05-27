package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc/users")
public class UserController {
	
	@Autowired
	private UserService userS;
	
	@GetMapping("/profile")
	public String profileView(@ModelAttribute("user") UserModel user, Model model) {
		
	}
	
	@GetMapping(9)
}
