package messengasesMvc.mvc_messenges.Controllers.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import messengasesMvc.mvc_messenges.Model.ChatModel;
import messengasesMvc.mvc_messenges.Model.LetterModel;
import messengasesMvc.mvc_messenges.Model.UserModel;

@Service
public class ChatService {

	@Autowired
	private RestTemplate fetch;
	
	public ChatModel getChatByUser(UserModel user, String token) {
		StringBuilder url = new StringBuilder("http://localhost:8080/api/chats/chatsByUser/").append(user.getId());

        HttpHeaders header = new HttpHeaders();
        header.setBearerAuth(token);

		HttpEntity<String> entity = new HttpEntity<>(header);

		ResponseEntity<ChatModel> response = fetch.exchange(url.toString(),HttpMethod.GET ,entity ,ChatModel.class);
		return response.getBody(); 
	}

	public List<ChatModel> getChatFromUser(UserModel user, String token){
        StringBuilder url = new StringBuilder("http://localhost:8080/api/chats/chatsByUser/").append(user.getId());

        HttpHeaders header = new HttpHeaders();
        header.setBearerAuth(token);
		
		HttpEntity<String> entity = new HttpEntity<>(header);  
		ResponseEntity<Mapper> response = new RestTemplate().exchange(url.toString(),HttpMethod.GET ,entity ,Mapper.class);
		return response.getBody().getListaChats();
	}
	
	public List<ChatModel> getUsersByChat(UserModel user, String token){
		StringBuilder url = new StringBuilder("http://localhost:8080/api/users/usersFromChats/").append(user.getId());

		HttpHeaders header = new HttpHeaders();
        header.setBearerAuth(token);

		HttpEntity<String> entity = new HttpEntity<>(header);
		ResponseEntity<Mapper> response = fetch.exchange(url.toString(),HttpMethod.GET ,entity,Mapper.class);
		return response.getBody().getListaChats();
	}
	
	
	public ChatModel getChatById(long id, String token) {
		StringBuilder url = new StringBuilder("http://localhost:8080/api/chats/").append(id);

		HttpHeaders header = new HttpHeaders();
        header.setBearerAuth(token);

		HttpEntity<String> entity = new HttpEntity<>(header);
		ResponseEntity<ChatModel> response = fetch.exchange(url.toString(),HttpMethod.GET ,entity,ChatModel.class);
		return response.getBody();
	}
	
	public void addIntegrator(ChatModel chat, UserModel user) {
		StringBuilder url = new StringBuilder("http://localhost:8080/api/chats/addUserToChat/").append(user.getId() + "/" + chat.getId());
		ResponseEntity<?> response = fetch.postForEntity(url.toString(), user, null);
	}
	
	public List<LetterModel> getLetterByChat(ChatModel chat, String token) {
		StringBuilder url = new StringBuilder("http://localhost:8080/api/messages/byChat/").append(chat.getId());

		HttpHeaders header = new HttpHeaders();
        header.setBearerAuth(token);

		HttpEntity<String> entity = new HttpEntity<>(header);
		ResponseEntity<Mapper> response = fetch.exchange(url.toString(),HttpMethod.GET,entity ,Mapper.class);
		return response.getBody().getListaMensaje();
	}

	public boolean createChat(ChatModel chatcito, String token) {
		StringBuilder url = new StringBuilder("http://localhost:8080/api/chats/createChat");

		HttpHeaders header = new HttpHeaders();
        header.setBearerAuth(token);

		HttpEntity<ChatModel> entity = new HttpEntity<>(chatcito,header);  

		ResponseEntity<ChatModel> response = fetch.exchange(url.toString(), HttpMethod.POST, entity, ChatModel.class);
		return response.getBody().equals("Chat Saved");
	}
	
	
	public void updateChat(ChatModel chatcito, String token) {
		StringBuilder url = new StringBuilder("http://localhost:8080/api/chats");

		HttpHeaders header = new HttpHeaders();
        header.setBearerAuth(token);

		HttpEntity<ChatModel> entity = new HttpEntity<>(chatcito,header);
		fetch.exchange(url.toString(),HttpMethod.PUT ,entity, String.class);
	}
	
	public void deleteChat(long id, String token) {
		StringBuilder url = new StringBuilder("http://localhost:8080/api/chats/").append(id);
		
		HttpHeaders header = new HttpHeaders();
        header.setBearerAuth(token);

		HttpEntity<String> entity = new HttpEntity<>(header);
		fetch.exchange(url.toString(),HttpMethod.DELETE, entity, String.class);
	}

	
}
