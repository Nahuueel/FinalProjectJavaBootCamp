package messengasesApi.api_messenges.Controller;

import java.util.List;

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
import messengasesApi.api_messenges.Model.Integrators;
import messengasesApi.api_messenges.Model.Users;
import messengasesApi.api_messenges.Services.ChatService;
import messengasesApi.api_messenges.Services.UserService;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

	@Autowired
	private ChatService chatService;
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/{id_chat}")
	public ResponseEntity<?> get(@PathVariable("id_chat") long id){
		Chats chat = chatService.get(id);
		if(chat!=null) 
			return ResponseEntity.ok().body(chat);
		else 
			return ResponseEntity.badRequest().body("Error");	
	}
	
	@GetMapping("/chatsByUser/{id_user}")
	public ResponseEntity<List<Chats>> getAllChatsByUser(@PathVariable("id_user") long id){
		List<Chats> chats = userService.getAllChatsByUser(id);
		if(!chats.isEmpty())
			return ResponseEntity.ok().body(chats);
		else 
			return ResponseEntity.badRequest().body(chats);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Chats chat){
		
		if(chatService.save(chat))
			return ResponseEntity.ok().body("Chat Saved");
		else 
			return ResponseEntity.badRequest().body("Error");
	}
	
	@PostMapping("/addUserToChat/{id_user}/{id_chat}")
	public ResponseEntity<?> save(@PathVariable("id_user") long idUser, @PathVariable("id_chat") long idChat){
		
		Integrators integrator = new Integrators();
		integrator.setUser(userService.get(idUser));
		integrator.setChat(chatService.get(idChat));
		if(chatService.saveIntegrator(integrator)) 
			return ResponseEntity.ok().body("Ingrator saved");
		else 
			return ResponseEntity.badRequest().body("Error");
			
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Chats chat){
		if(chatService.update(chat))
			return ResponseEntity.ok().body("Chat Updated");
		else 
			return ResponseEntity.badRequest().body("Error");
	}
	
	@DeleteMapping("/{id_chat}")
	public ResponseEntity<?> delete(@PathVariable("id_chat") long id ){
		
		if(chatService.delete(id))
			return ResponseEntity.ok().body("Chat Deleted");
		else 
			return ResponseEntity.badRequest().body("Error");
	}
}
