package controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc/chats")
public class ChatController {
	
	@Autowired 
	private ChatService chatS;
	
	@Autowired
	private UserService userS;
	
	UserModel user;
	
	@GetMapping("/principal/{id_user}/{id_chat}")
	public String showChats(Model model, @PathVariable("id_user") long idUser,@PathVariable("id_chat") long idChat) {
		user = userS.getUserById(idUser);
		ChatModel chat = chatS.getChatById(idChat);
		ArrayList<ChatModel> chats = chatS.getChatFromUser(user);	;// aca va el service que trae todos los chats de un usuario
		ArrayList<LetterModel> msg = chatS.getLetterByChat(chat);// aca tengo que traer La lista de mensajes de un chat
		
		
		model.addAttribute("chats", chats);
		model.addAttribute("mesagges", msg);
		model.addAtribute("newMsg", new LetterModel());
		
		return "templates/chats";
	}
	
	@PostMapping("/")
	public String 
	
	
	
}
