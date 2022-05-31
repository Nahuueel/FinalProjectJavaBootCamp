package messengasesMvc.mvc_messenges.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import messengasesMvc.mvc_messenges.Controllers.Services.UserService;
import messengasesMvc.mvc_messenges.Model.UserModel;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userS;
	
	@GetMapping("/profile")
	public String profileView(@ModelAttribute("user") UserModel userLogin, Model model) {
		model.addAttribute("user", userLogin);
		return "profile";
	}
	
	@PostMapping("/modify")
	public String modifyUser(@ModelAttribute("user") UserModel userLogin, Model model) {
		model.addAttribute("user", userLogin);
		if(userLogin.getUsername()!= null && userLogin.getPassword()!=null) {			
			userS.updateUser(userLogin);
			model.addAttribute("idChat", 1);
			return "redirect:/chats/principal"; 
		} else 
			return "redirect:/profile";
	}
	
	@PostMapping("/delete")
	public String deleteUser(@ModelAttribute("user") UserModel userLogin) {
		userS.deleteUser(userLogin.getId());
		return "redirect:/index/login";
	}
	
	@PostMapping("/back")
	public String goBack(Model model, @ModelAttribute("user") UserModel userLogin) {
		model.addAttribute("user", userLogin);
		model.addAttribute("idChat", 1);
		return "redirect:/chats/principal";
	}
}
