package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc/index")
public class LoginController {

	@Autowired 
	private UserService userS;
	
	private String token;
	
	@GetMapping("/login")
	public String loginTemplate(Model model) {
		model.addAttribute("user", new UserModel());
		return "templates/index";
	}
	
	@PostMapping("/signin")
	public String login(@ModelAttribute("user") UserModel user) {
		try {
			token = userS.login(user);
			
			return "redirect:/mvc/chats/principal/"+user.getId()+"/1";
		} catch(Exception e) {
			return "redirect:/login";
		}
		
	}
	
	@GetMapping("/signup")
	public String signUpTemplate(Model model) {
		model.addAttribute("user",new UserModel());
		return "templates/sign_up";
	}
	
	@PostMapping("/signup")
	public String signUp(@ModelAttribute("user") UserModel user) {
		if(userS.createUser(user))
			return "redirect:/login";
		else
			return "redirect:/signup";	//En el html hacer el href en vez del onclick para poder cargar el model
										//aca tambien puede tirar un error con una ventanita o toast o algo
	}
}
