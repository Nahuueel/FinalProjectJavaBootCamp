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

public class MessageController {
	
	@Autowired
	private	MessageService msgService;
	
	@GetMapping
	public ResponseEntity<List<Message>> getAll(){
		ArrayList<Message> messages = msgService.getAll();
	}
	
	@GetMapping("/{id_msg}")
	public ResponseEntity<Message> get(@PathVariable("id_msg") long id){
		Message msg = msgService.get(id);
		if(msg != null)
			return ResponseEntity.ok().body(msg);
		else
			return ResponseEntity.badRequest().body(msg);
	}
	
	@GetMapping("/byUser/{id_user}")
	public ResponseEntity<List<Message>> getAllByUser(@PathVariable("id_user") long id){
		ArrayList<Message> messages = msgService.getByFullname(fullName);
		if(messages != null)
			return ResponseEntity.ok().body(messages);
		else 
			return ResponseEntity.badRequest().body(messages);
	}
	
	@GetMapping("/byChat/{id_chat}")
	public ResponseEntity<List<Message>> getAllByChat(@PathVariable("id_chat") long id){
		ArrayList<Message> messages = msgService.getAllByChat(fullName);
		if(messages != null)
			return ResponseEntity.ok().body(messages);
		else 
			return ResponseEntity.badRequest().body(messages);
	}
	
	@PostMapping
	public ResponseEntity<boolean> save(@RequestBody Message msg){
		
		if(msgService.save(msg))
			return ResponseEntity.ok().body(msg);
		else 
			return ResponseEntity.badRequest();
	}
	
	@PutMapping
	public ResponseEntity<boolean> update(@RequestBody Message msg){
		
		if(msgService.update(msg))
			ResponseEntity.ok();
		else 
			ResponseEntity.badRequest();
	}
	
	@DeleteMapping("/{id_msg}")
	public ResponseEntity<boolean> delete(@PathVariable("id_msg") long id ){
		
		if(msgService.delete(id))
			ResponseEntity.ok();
		else 
			ResponseEntity.badRequest();
	}
}
