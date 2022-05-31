package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import messengasesMvc.mvc_messenges.Model.UserModel;
import messengasesMvc.mvc_messenges.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userS;
	
	@GetMapping("/profile/{idUser}")
	public String profileView(@PathVariable("idUser") long idUser, Model model) {
		model.addAttribute("user", userS.getUserById(idUser));
		return "profile";
	}
	
	@PostMapping("/modify")
	public String modifyUser(@ModelAttribute("user") UserModel userLogin) {
		long idUser = userLogin.getId();
		if(userLogin.getUsername()!= null && userLogin.getPassword()!=null) {			
			userS.updateUser(userLogin);
			return "redirect:/chats/principal/"+idUser; 
		} else 
			return "redirect:/profile/"+idUser;
	}
	
	@PostMapping("/delete")
	public String deleteUser(@ModelAttribute("user") UserModel userLogin) {
		userS.deleteUser(userLogin.getId());
		return "redirect:/index/login";
	}
	
	@PostMapping("/back")
	public String goBack(@ModelAttribute("user") UserModel userLogin) {
		long idUser = userLogin.getId();
		long idChat = 1;
		return "redirect:/chats/principal/"+idUser+"/"+idChat;
	}
}
