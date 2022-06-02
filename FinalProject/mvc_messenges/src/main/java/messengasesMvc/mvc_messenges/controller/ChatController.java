package messengasesMvc.mvc_messenges.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import messengasesMvc.mvc_messenges.Model.ChatModel;
import messengasesMvc.mvc_messenges.Model.LetterModel;
import messengasesMvc.mvc_messenges.Model.UserModel;
import messengasesMvc.mvc_messenges.Model.createChatDTO;
import messengasesMvc.mvc_messenges.services.ChatService;
import messengasesMvc.mvc_messenges.services.LetterService;
import messengasesMvc.mvc_messenges.services.UserService;

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
	public String showChats(Model model, @PathVariable("idUser") long idUser, @PathVariable("idChat") long idChat) {
		UserModel user = userS.getUserById(idUser);
		ChatModel chat = chatS.getChatById(idChat);
		ArrayList<ChatModel> chats = (ArrayList<ChatModel>) chatS.getChatFromUser(user);	// aca en realidad va con la paginacion para mostrar 10
		ArrayList<LetterModel> msgs = (ArrayList<LetterModel>) chatS.getLetterByChat(chat);	// aca tengo que traer La lista de mensajes de un chat
		
		model.addAttribute("user",user);
		model.addAttribute("chats", chats);
		model.addAttribute("mesagges", msgs);
		model.addAttribute("newMsg", new LetterModel());
		model.addAttribute("chatSelected", chat);
		
		return "chats";
	}
	
	@PostMapping("/sendMsg")
	public String sendChat(@ModelAttribute("newMsg") LetterModel msg, @ModelAttribute("chats") ChatModel chat, @ModelAttribute("user") UserModel userLogin, Model model) {
		if(msg.getContent()!=null) {
			msg.setChat(chat);
			msg.setUser(userLogin);
			letS.createLetter(msg);
		}
		model.addAttribute("user", userLogin);
		model.addAttribute("idChat",chat.getId());
		return "redirect:/principal";
	}
	
	@PostMapping("/profile")
	public String profileView(@ModelAttribute("user") UserModel userLogin) {
		long idUser = userLogin.getId();
		return "redirect:/users/profile/"+idUser; 
	}
	
	@PostMapping("/createChatView")
	public String goCreateChat(@ModelAttribute("user") UserModel userLogin) {
		long idUser = userLogin.getId();
		return "redirect:/createChat/"+idUser;
	}
	
	@GetMapping("/createChat/{idUser}")
	public String createChatView(Model model, @PathVariable("idUser") long idUser) {
		UserModel userLogin = userS.getUserById(idUser);
		createChatDTO chatDto= new createChatDTO();
		chatDto.setUser(userLogin);
		
		model.addAttribute("chatDto", chatDto);
		return "create_chats";
	}
	
	@PostMapping("/createChat")
	public String createChat(@ModelAttribute("chatDto") createChatDTO chatDto) {
		long idUser = chatDto.getUser().getId();
		long idChat = 1;
		UserModel user2 = userS.getUserByUsername(chatDto.getIntegratorUsername()));
		if(user2!=null) {
			ChatModel chat = new ChatModel();
			chat.setName(chatDto.getChatName());
			chatS.createChat(chat);
			chatS.addIntegrator(chat, chatDto.getUser()); //falta el service addIntegrator
			chatS.addIntegrator(chat, user2);
			return "redirect:/principal/"+idUser+"/"+idChat;
		} else 
			return "redirect:/createChat/"+idUser;	
	}
	
	@PostMapping("/delete")
	public String deleteUser(@ModelAttribute("user") UserModel userLogin) {
		userS.deleteUser(userLogin.getId());
		return "redirect:/login";
	}
	
}
