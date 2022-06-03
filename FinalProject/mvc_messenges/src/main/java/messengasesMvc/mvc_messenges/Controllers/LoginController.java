package messengasesMvc.mvc_messenges.Controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import messengasesMvc.mvc_messenges.Controllers.Services.CookieService;
import messengasesMvc.mvc_messenges.Controllers.Services.UserService;
import messengasesMvc.mvc_messenges.Model.UserModel;

@Controller
@RequestMapping("/index")
public class LoginController {

	@Autowired 
	private UserService userS;
	
	@Autowired
	private CookieService cookS;

	@GetMapping("/login")
	public String loginTemplate(Model model) {
		model.addAttribute("user", new UserModel());
		return "index";
	}
	
	@PostMapping("/signin")
	public String login(@ModelAttribute("user") UserModel userLogin, Model model) {

		String token = userS.login(userLogin);

		cookS.createCoockie(token);

		UserModel user = userS.getUserByUsername(userLogin.getUsername(),token); 
		long idUser = user.getId();
		return "redirect:/chats/principal/"+idUser;	
	}
	
	@GetMapping("/signup")
	public String signUpTemplate(Model model) {
		model.addAttribute("user",new UserModel());
		return "sign_up";
	}
	
	@PostMapping("/signup")
	public String signUp(@ModelAttribute("user") UserModel userLogin) {
		if(userS.createUser(userLogin)) {
			return "redirect:/index/login";			
		}
		else
			return "redirect:/index/signup";
	}

	@GetMapping("/logout")
	public String logout(){
		cookS.deleteCookie();
		return "redirect:/index/login";
	}
}
