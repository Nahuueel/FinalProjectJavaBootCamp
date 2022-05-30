package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import messengasesMvc.mvc_messenges.Model.UserModel;
import messengasesMvc.mvc_messenges.services.UserService;

@Controller
@RequestMapping("/index")
public class LoginController {

	@Autowired 
	private UserService userS;
	
	private String token;
	
	@GetMapping("/login")
	public String loginTemplate(Model model) {
		model.addAttribute("user", new UserModel());
		return "index";
	}
	
	@PostMapping("/signin")
	public String login(@ModelAttribute("user") UserModel userLogin, Model model) {
//		try {
//			token = userS.login(user);
//			redAt.addFlashAttribute("token", token);
//			
//			return "redirect:/chats/principal/1/"+user.getUsername();
//		} catch(Exception e) {
//			return "redirect:/login";
		
//		}
		UserModel user = userS.getUserByUsername(userLogin.getUsername());
		model.addAttribute(user);
		return "redirect:/chats/principal";	
	}
	
	@GetMapping("/signup")
	public String signUpTemplate(Model model) {
		model.addAttribute("user",new UserModel());
		return "sign_up";
	}
	
	@PostMapping("/signup")
	public String signUp(@ModelAttribute("user") UserModel userLogin, Model model) {
		if(userS.createUser(userLogin)) {
			model.addAttribute("user",userLogin);
			model.addAttribute("idChat",1);
			return "redirect:/login";			
		}
		else
			return "redirect:/signup";	//En el html hacer el href en vez del onclick para poder cargar el model
										//aca tambien puede tirar un error con una ventanita o toast o algo
	}
}
