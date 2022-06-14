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
import messengasesMvc.mvc_messenges.Model.ChatsList;
import messengasesMvc.mvc_messenges.Model.LetterModel;
import messengasesMvc.mvc_messenges.Model.LettersList;
import messengasesMvc.mvc_messenges.Model.UserModel;

@Service
public class ChatService {

	@Autowired
	private RestTemplate fetch;

	@Autowired
	private HttpHeaders header;
	
	public ChatModel getChatByUser(UserModel user, String token) {
		StringBuilder url = new StringBuilder("http://localhost:8080/api/chats/chatsByUser/").append(user.getId());

		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
        
		ResponseEntity<ChatModel> response = fetch.exchange(url.toString(),HttpMethod.GET ,entity ,ChatModel.class);
		return response.getBody(); 
	}

	public List<ChatModel> getChatFromUser(UserModel user, String token){
        StringBuilder url = new StringBuilder("http://localhost:8080/api/chats/chatsByUser/").append(user.getId());

		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
        
		ResponseEntity<ChatsList> response = new RestTemplate().exchange(url.toString(),HttpMethod.GET ,entity,ChatsList.class);
		return response.getBody().getChats();
	}
	
	public List<ChatModel> getUsersByChat(UserModel user, String token){
		StringBuilder url = new StringBuilder("http://localhost:8080/api/users/usersFromChats/").append(user.getId());

		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
        
	//	ResponseEntity<> response = fetch.exchange(url.toString(),HttpMethod.GET ,entity,Mapper.class);
		return null; // response.getBody().getListaChats();
	}
	
	
	public ChatModel getChatById(long id, String token) {
		StringBuilder url = new StringBuilder("http://localhost:8080/api/chats/").append(id);

		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
        
		ResponseEntity<ChatModel> response = fetch.exchange(url.toString(),HttpMethod.GET ,entity,ChatModel.class);
		return response.getBody();
	}
	
	public void addIntegrator(long chat, long user, String token) {
		StringBuilder url = new StringBuilder("http://localhost:8080/api/chats/addUserToChat/").append(user + "/" + chat);

		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
        
		ResponseEntity<String> response = fetch.exchange(url.toString(),HttpMethod.POST, entity, String.class);
	}
	
	public List<LetterModel> getLetterByChat(ChatModel chat, String token) {
		StringBuilder url = new StringBuilder("http://localhost:8080/api/messages/byChat/").append(chat.getId());

		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
        
		ResponseEntity<LettersList> response = fetch.exchange(url.toString(),HttpMethod.GET,entity ,LettersList.class);
		return response.getBody().getMsgs();
	}

	public ChatModel createChat(ChatModel chatcito, String token) {
		StringBuilder url = new StringBuilder("http://localhost:8080/api/chats");

		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);

		ResponseEntity<ChatModel> response = fetch.exchange(url.toString(), HttpMethod.POST, entity, ChatModel.class);
		return response.getBody(); 
	}
	
	
	public void updateChat(ChatModel chatcito, String token) {
		StringBuilder url = new StringBuilder("http://localhost:8080/api/chats");

		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
        
		fetch.exchange(url.toString(),HttpMethod.PUT ,entity, String.class);
	}
	
	public void deleteChat(long id, String token) {
		StringBuilder url = new StringBuilder("http://localhost:8080/api/chats/").append(id);

		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
        
		fetch.exchange(url.toString(),HttpMethod.DELETE, entity, String.class);
	}

	
}
