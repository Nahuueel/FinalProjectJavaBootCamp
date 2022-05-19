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
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{id_user}")
	public ResponseEntity<Users> get(@PathVariable("id_user") long id){
		Users user = userService.get(id);
		if(user != null)
			return ResponseEntity.ok().body(user);
		else
			return ResponseEntity.badRequest().body(user);
	}
	
	@GetMapping("/byUsername")
	public ResponseEntity<Users> getByUsername(@RequestBody String username){
		Users user = userService.getByUsername(username);
		if(user!= null) 
			return ResponseEntity.ok().body(user);
		else
			return ResponseEntity.badRequest().body(user);
	}
	
	@GetMapping("/allByFullName")
	public ResponseEntity<List<Users>> getAllByFullName(@RequestBody String fullName){
		ArrayList<Users> users = userService.getByFullname(fullName);
		if(!users.isEmpty())
			return ResponseEntity.ok().body(users);
		else 
			return ResponseEntity.badRequest().body(users);
	}
	
	@GetMapping("/chatsByUser/{id_user}")
	public ResponseEntity<List<Chats>> getAllChatsByUser(@PathVariable("id_user") long id){
		ArrayList<Chats> chats = userService.getChatByUser();
		if(!chats.isEmpty())
			return ResponseEntity.ok().body(chats);
		else 
			return ResponseEntity.badRequest().body(chats);
	}
	
	@PostMapping
	public ResponseEntity<boolean> save(@RequestBody User user){
		
		if(userService.save(user))
			return ResponseEntity.ok().body(user);
		else 
			return ResponseEntity.badRequest();
	}
	
	@PutMapping
	public ResponseEntity<boolean> update(@RequestBody User user){
		
		if(userService.update(user))
			ResponseEntity.ok();
		else 
			ResponseEntity.badRequest();
	}
	
	@DeleteMapping("/{id_user}")
	public ResponseEntity<boolean> delete(@PathVariable("id_user") long id ){
		
		if(userService.delete(id))
			ResponseEntity.ok();
		else 
			ResponseEntity.badRequest();
	}
	
	
	
	
	
}
