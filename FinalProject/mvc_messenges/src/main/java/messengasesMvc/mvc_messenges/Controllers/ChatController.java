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
	
	
	@GetMapping("/principal/{idUser}/{idChat}")
	public String showChats(Model model, 
        @CookieValue(name = "TokenCookie",required=true) String TokenCookie, 
        @PathVariable("idUser") long idUser,
        @PathVariable("idChat") long idChat) {
		
		UserModel user = userS.getUserById(idUser,TokenCookie);
		ArrayList<ChatModel> chats = new ArrayList<>();
		ChatModel chat = null;
		ArrayList<LetterModel> letters = new ArrayList<>();
		LetterModel letter = new LetterModel();
		letter.setIdUser(idUser);
		letter.setIdChat(idChat);
		
		try {
			chats = (ArrayList<ChatModel>) chatS.getChatFromUser(user,TokenCookie);
		} catch(Exception e){
			model.addAttribute("user", user);
			return "chats";
		}
		try {
			chat = chatS.getChatById(idChat, TokenCookie);
		} catch(Exception e) {
			model.addAttribute("user", user);
			model.addAttribute("chats", chats);
			return "chats";
		}
		
		try {
			letters = (ArrayList<LetterModel>) letS.getLetterByChat(chat, TokenCookie);
		} catch(Exception e) {
			model.addAttribute("user", user);
			model.addAttribute("chats", chats);
			model.addAttribute("chat", chat);
			model.addAttribute("newMsg", letter);
			return "chats";
		}
		
		model.addAttribute("newMsg", letter);
		model.addAttribute("chat", chat);
		model.addAttribute("msg", letters);
		model.addAttribute("chats", chats);
		model.addAttribute("user", user);
		return "chats";
		
	}
	
	@PostMapping("/sendMsg")
	public String sendChat(	@ModelAttribute("newMsg") LetterModel msg,
							@CookieValue(name = "TokenCookie",required=true) String TokenCookie) {
		
		
		System.out.println(msg.getContent() + msg.getIdChat() + msg.getIdUser());
		if(msg.getContent()!=null) {
			letS.createLetter(msg,TokenCookie);
		}
		
		return "redirect:/chats/principal/"+msg.getIdUser()+"/"+msg.getIdChat();
	}
	/*
	@PostMapping("/profile")
	public String profileView(@ModelAttribute("user") UserModel userLogin) {
		long idUser = userLogin.getId();
		return "redirect:/users/profile/"+idUser; 
	}
	*/
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
			return "redirect:/chats/principal/"+userLogin.getId()+"/0";
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
	

	
}