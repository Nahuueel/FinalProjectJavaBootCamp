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
import messengasesApi.api_messenges.Model.Users;
import messengasesApi.api_messenges.Services.ChatService;
import messengasesApi.api_messenges.Services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatService chatService;
	
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
		List<Users> users = userService.getAllByFullname(fullName);
		if(!users.isEmpty())
			return ResponseEntity.ok().body(users);
		else 
			return ResponseEntity.badRequest().body(users);
	}
	
	@GetMapping("/usersFromChat/{id_chat}")
	public ResponseEntity<List<Users>> getUsersFromChat(@PathVariable("id_chat") long id){
		List<Users> users = chatService.getAllUserByChat(id);
		if(users!=null) 
			return ResponseEntity.ok().body(users);
		else 
			return ResponseEntity.badRequest().body(users);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> save(@RequestBody Users user){
		
		if(userService.save(user))
			return ResponseEntity.ok().body("User Saved");
		else 
			return ResponseEntity.badRequest().body("Error");
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Users user){
		
		if(userService.update(user))
			return ResponseEntity.ok().body("User Updated");
		else 
			return ResponseEntity.badRequest().body("Error");
	}
	
	@DeleteMapping("/{id_user}")
	public ResponseEntity<?> delete(@PathVariable("id_user") long id ){
		if(userService.delete(id))
			return ResponseEntity.ok().body("User Deleted");
		else 
			return ResponseEntity.badRequest().body("Error");
	}
	
	
	
	
	
}
