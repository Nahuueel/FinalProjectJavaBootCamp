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

import messengasesApi.api_messenges.Model.Chats;
import messengasesApi.api_messenges.Model.Users;
import messengasesApi.api_messenges.Services.ChatService;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

	@Autowired
	private ChatService chatService;
	
	@GetMapping
	public ResponseEntity<List<Chats>> getAll(){
		List<Chats> chats = chatService.getAll();
		if(chats!=null) 
			return ResponseEntity.ok().body(chats);
		else 
			return ResponseEntity.badRequest().body(chats);	
	}
	
	@GetMapping("/{id_chat}")
	public ResponseEntity<?> get(@PathVariable("id_chat") long id){
		Chats chat = chatService.get(id);
		if(chat!=null) 
			return ResponseEntity.ok().body(chat);
		else 
			return ResponseEntity.badRequest().body("Error");	
	}
	
	@GetMapping("/byUsername")
	public ResponseEntity<List<Chats>> getChatsByName(@RequestBody String name){
		List<Chats> chats = chatService.getByName(name);
		if(chats!=null) 
			return ResponseEntity.ok().body(chats);
		else 
			return ResponseEntity.badRequest().body(chats);
	}
	
	@GetMapping("/usersFromChat/{id_chat}")
	public ResponseEntity<List<Users>> getUsersFromChat(@PathVariable("id_chat") long id){
		List<Users> users = chatService.getAllUserByChat(id);
		if(users!=null) 
			return ResponseEntity.ok().body(users);
		else 
			return ResponseEntity.badRequest().body(users);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Chats chat){
		
		if(chatService.save(chat))
			return ResponseEntity.ok().body("Chat Saved");
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
