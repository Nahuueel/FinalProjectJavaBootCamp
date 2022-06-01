package messengasesMvc.mvc_messenges.Controllers;

import java.util.ArrayList;
import java.util.List;

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
	
	
	@GetMapping("/principal")
	public String showChats(Model model, @ModelAttribute("user") UserModel userLogin, @ModelAttribute("idChat") long idChat) {
		//UserModel user = userS.getUserById(idUser);
		UserModel user = userS.getUserByUsername(userLogin.getUsername());
		ChatModel chat = chatS.getChatById(idChat);
		List<ChatModel> chats = (ArrayList<ChatModel>) chatS.getUsersByChat(user);	// aca en realidad va con la paginacion para mostrar 10
		List<LetterModel> msgs = chatS.getLetterByChat(chat);	// aca tengo que traer La lista de mensajes de un chatS
		List<LetterModel> translatedMsg = new ArrayList<>();
		for(LetterModel msg: msgs){
			msg.translate();
			translatedMsg.add(msg);
		}
		
		model.addAttribute("user",user);
		model.addAttribute("chats", chats);
		model.addAttribute("mesagges", translatedMsg);
		model.addAttribute("newMsg", new LetterModel());
		
		return "templates/chats";
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
	public String profileView(Model model, @ModelAttribute("user") UserModel userLogin) {
		model.addAttribute("user", userLogin);
		return "redirect:/users/profile"; 
	}
	
	@PostMapping("/createChatView")
	public String goCreateChat(Model model, @ModelAttribute("user") UserModel userLogin) {
		model.addAttribute("user", userLogin);
		return "redirect:/createChat";
	}
	
	@GetMapping("/createChat")
	public String createChatView(Model model, @ModelAttribute("user") UserModel userLogin) {
		model.addAttribute("integrator", new UserModel());
		model.addAttribute("user", userLogin);
		model.addAttribute("chat", new ChatModel());
		return "create_chats";
	}
	
	@PostMapping("/createChat")
	public String createChat(Model model, @ModelAttribute("user") UserModel userLogin, @ModelAttribute("integrator") UserModel integrator, @ModelAttribute("chat") ChatModel chat) {
		UserModel user2 = userS.getUserByUsername(integrator.getUsername());
		if(user2!=null) {
			chatS.createChat(chat);
			chatS.addIntegrator(chat, userLogin); //falta el service addIntegrator
			chatS.addIntegrator(chat, user2);
			model.addAttribute("user", userLogin);
			model.addAttribute("id_chat",chat.getId());
			return "redirect:/principal";
		} else 
			model.addAttribute("user", userLogin);
			return "redirect:/createChat";	
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
		return "redirect:/principal";
	}
}
