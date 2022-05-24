package messengasesApi.api_messenges.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import messengasesApi.api_messenges.Model.Chats;
import messengasesApi.api_messenges.Model.Messages;
import messengasesApi.api_messenges.Model.Users;
import messengasesApi.api_messenges.Services.ChatService;
import messengasesApi.api_messenges.Services.MessageService;
import messengasesApi.api_messenges.Services.UserService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private	MessageService msgService;
	

	@GetMapping("/{id_msg}")
	public ResponseEntity<?> get(@PathVariable("id_msg") long id){
		Optional<Messages> msg = msgService.get(id);
		if(msg != null)
			return ResponseEntity.ok().body(msg.get());
		else
			return ResponseEntity.badRequest().body("Error");
	}
	
	@GetMapping("/byUser/{id_user}")
	public ResponseEntity<List<Messages>> getAllByUser(@PathVariable("id_user") long id){
		List<Messages> messages = msgService.getAllByUser(id);
		if(messages != null)
			return ResponseEntity.ok().body(messages);
		else 
			return ResponseEntity.badRequest().body(messages);
	}
	
	@GetMapping("/byChat/{id_chat}")
	public ResponseEntity<List<Messages>> getAllByChat(@PathVariable("id_chat") long id){
		List<Messages> messages = msgService.getAllByChat(id);
		if(messages != null)
			return ResponseEntity.ok().body(messages);
		else 
			return ResponseEntity.badRequest().body(messages);
	}
	
	@PostMapping("/{id_user}/{id_chat}")
	public ResponseEntity<?> save(@RequestBody String content, @PathVariable("id_user") long idUser, @PathVariable("id_chat") long idChat){
		Users user = userService.get(idUser);
		Chats chat = chatService.get(idChat);
		Messages msg = new Messages();
		
		msg.setUser(user);
		msg.setChat(chat);
		msg.setContent(content);
		
		if(msgService.save(msg))
			return ResponseEntity.ok().body("Message Saved");
		else 
			return ResponseEntity.badRequest().body("Error");
	}

	@DeleteMapping("/{id_msg}")
	public ResponseEntity<?> delete(@PathVariable("id_msg") long id ){
		
		if(msgService.delete(id))
			return ResponseEntity.ok().body("Message Deleted");
		else 
			return ResponseEntity.badRequest().body("Error");
	}
}
