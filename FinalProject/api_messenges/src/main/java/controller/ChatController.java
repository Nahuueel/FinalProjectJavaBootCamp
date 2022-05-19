package controller;

import java.util.ArrayList;
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

@RestController
@RequestMapping("/api/chats")
public class ChatController {

	@Autowired
	private ChatService chatService;
	
	@GetMapping
	public ResponseEntity<List<Chat>> getAll(){
		ArrayList<Chat> chats = chatService.getAll();
		if(chats!=null) 
			return ResponseEntity.ok().body(chats);
		else 
			return ResponseEntity.badRequest().body(chats);	
	}
	
	@GetMapping("/{id_chat}")
	public ResponseEntity<Chat> get(@PathVariable("id_chat") long id){
		ArrayList<Chat> chat = chatService.get(id);
		if(chat!=null) 
			return ResponseEntity.ok().body(chat);
		else 
			return ResponseEntity.badRequest().body(chat);	
	}
	
	@GetMapping("/byUsername")
	public ResponseEntity<List<Chat>> getChatsByName(@RequestBody String name){
		ArrayList<Chat> chats = chatService.getByName(name);
		if(chats!=null) 
			return ResponseEntity.ok().body(chats);
		else 
			return ResponseEntity.badRequest().body(chats);
	}
	
	@GetMapping("/usersFromChat/{id_chat}")
	public ResponseEntity<List<User>> getUsersFromChat(@PathVariable("id_chat") long id){
		ArrayList<User> users = chatService.getAllUser(id);
		if(users!=null) 
			return ResponseEntity.ok().body(users);
		else 
			return ResponseEntity.badRequest().body(users);
	}
	
	@PostMapping
	public ResponseEntity<boolean> save(@RequestBody Chat chat){
		
		if(chatService.save(chat))
			ResponseEntity.ok();
		else 
			ResponseEntity.badRequest();
	}
	
	@PutMapping
	public ResponseEntity<boolean> update(@RequestBody Chat chat){
		if(userService.update(user))
			ResponseEntity.ok();
		else 
			ResponseEntity.badRequest();
	}
	
	@DeleteMapping("/{id_chat}")
	public ResponseEntity<boolean> delete(@PathVariable("id_chat") long id ){
		
		if(chatService.delete(id))
			ResponseEntity.ok();
		else 
			ResponseEntity.badRequest();
	}
}
