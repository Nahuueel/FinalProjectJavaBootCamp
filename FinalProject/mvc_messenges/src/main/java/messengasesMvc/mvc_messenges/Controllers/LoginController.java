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

import messengasesMvc.mvc_messenges.Controllers.Services.UserService;
import messengasesMvc.mvc_messenges.Model.UserModel;

@Controller
@RequestMapping("/index")
public class LoginController {

	@Autowired 
	private UserService userS;
	
	@GetMapping("/login")
	public String loginTemplate(Model model) {
		model.addAttribute("user", new UserModel());
		return "index";
	}
	
	@PostMapping("/signin")
	public String login(@ModelAttribute("user") UserModel userLogin, HttpServletResponse response, Model model) {
		try {
			String token = userS.login(userLogin);			
			Cookie uiTokenCookie = new Cookie("TokenCookie", token);
			uiTokenCookie.setMaxAge(60 * 60);
			uiTokenCookie.setSecure(true);
			uiTokenCookie.setHttpOnly(true);
			uiTokenCookie.setPath("/");
			
			response.addCookie(uiTokenCookie);
			
			System.out.println(token);
			
			UserModel user = userS.getUserByUsername(userLogin.getUsername(),token); 
			long idUser = user.getId();
			return "redirect:/chats/principal/"+idUser;	
		} catch (Exception e) {
			return "redirect:/index/login";
		}

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
}
