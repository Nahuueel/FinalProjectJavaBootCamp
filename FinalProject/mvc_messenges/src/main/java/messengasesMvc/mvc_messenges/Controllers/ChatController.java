package messengasesMvc.mvc_messenges.Controllers;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import messengasesMvc.mvc_messenges.Controllers.Services.ChatService;
import messengasesMvc.mvc_messenges.Controllers.Services.LetterService;
import messengasesMvc.mvc_messenges.Controllers.Services.UserService;
import messengasesMvc.mvc_messenges.Model.ChatModel;
import messengasesMvc.mvc_messenges.Model.LetterModel;
import messengasesMvc.mvc_messenges.Model.UserModel;
import messengasesMvc.mvc_messenges.Model.createChatDTO;

@Controller
@RequestMapping("/chats")
public class ChatController {
	
	@Autowired 
	private ChatService chatS;
	
	@Autowired
	private UserService userS;
	
	@Autowired
	private LetterService letS;
	
	
	@GetMapping("/principal/{idUser}")
	public String showChats(Model model, 
        @CookieValue(name = "TokenCookie",required=true) String TokenCookie, 
        @PathVariable("idUser") long idUser) {
		
		UserModel user = userS.getUserById(idUser,TokenCookie); 
	/*	ArrayList<ChatModel> chats = new ArrayList<>();
		try{
			chats = (ArrayList<ChatModel>) chatS.getChatFromUser(user.getId(),TokenCookie);
		}catch(Exception e){
			if(e!=null){
				model.addAttribute("user", user);
				return"chats.html";
			}
		}
		*/
		model.addAttribute("user", user);
//		model.addAttribute("chats", chats);
		return "chats";
	}
	
	@PostMapping("/sendMsg")
	public String sendChat(@ModelAttribute("newMsg") LetterModel msg, 
        @ModelAttribute("chats") ChatModel chat, 
        @ModelAttribute("user") UserModel userLogin, 
        @CookieValue(name = "TokenCookie",required=true) String TokenCookie,
        Model model) {
		if(msg.getContent()!=null) {
			msg.setChat(chat);
			msg.setUser(userLogin);
			letS.createLetter(msg,TokenCookie);
		}
		model.addAttribute("user", userLogin);
		model.addAttribute("idChat",chat.getId());
		return "redirect:/principal";
	}
	
	/* 
	@PostMapping("/profile")
	public String profileView(@ModelAttribute("user") UserModel userLogin) {
		long idUser = userLogin.getId();
		return "redirect:/users/profile/"+idUser; 
	}*/
	
	@PostMapping("/createChat")
	public String createChat( 
        @CookieValue(name = "TokenCookie",required=true) String TokenCookie,    
        @ModelAttribute("chatDTO") createChatDTO chatDto) {
		
		UserModel userLogin = userS.getUserById(chatDto.getIdUserC(), TokenCookie);
		UserModel integrator = userS.getUserByUsername(chatDto.getIntegratorUsername(), TokenCookie);

		ChatModel chat = new ChatModel();
		chat.setName(chatDto.getChatName()); 
		chat = chatS.createChat(chat, TokenCookie);

		if(integrator!=null) {
			chatS.addIntegrator(chat.getId(), userLogin.getId(), TokenCookie); 
			chatS.addIntegrator(chat.getId(), integrator.getId(), TokenCookie);
			return "redirect:/chats/principal/"+userLogin.getId();
		} else 
			return "redirect:/chats/createChat/"+userLogin.getId();	
		}


	@GetMapping("/createChat/{idUser}")
	public String createChatView(Model model, 
        @CookieValue(name = "TokenCookie",required=true) String TokenCookie,
        @PathVariable("idUser") long idUser) {
		UserModel userLogin = userS.getUserById(idUser,TokenCookie);
		
		createChatDTO newChat = new createChatDTO();
		newChat.setIdUserC(idUser);		

		model.addAttribute("chatDTO", newChat);
		model.addAttribute("user", userLogin);
		return "create_chats";
	}
	
	@PostMapping("/delete")
	public String deleteUser(@CookieValue(name = "TokenCookie",required=true) String TokenCookie,
        @ModelAttribute("user") UserModel userLogin) {
		userS.deleteUser(userLogin.getId(),TokenCookie);
		return "redirect:/index/login";
	}
	
}