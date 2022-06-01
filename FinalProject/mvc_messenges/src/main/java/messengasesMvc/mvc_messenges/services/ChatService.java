package messengasesMvc.mvc_messenges.services;


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
	
	@Autowired
	private HttpHeaders header;
	
	@Autowired
    private CookieService cookie;
	
	private StringBuilder url = new StringBuilder("http://localhost:8080/api/chats");
	
	//Consultar con nico el tema de enviar token a travez del header
	public ChatModel getChatByUser(UserModel user) {
		String token = cookie.readCookie("");
		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
		ResponseEntity<ChatModel> response = fetch.exchange(url.append("/chatsByUser/" + user.getId()).toString(),HttpMethod.GET ,entity ,ChatModel.class);
		return response.getBody();
	}
	
	public List<ChatModel> getUsersByChat(UserModel user){
		String token = cookie.readCookie("");
		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
		ResponseEntity<Mapper> response = fetch.exchange(url.append("/usersFromChats/" + user.getId()).toString(),HttpMethod.GET ,entity,Mapper.class);
		return response.getBody().getListaChats();
	}
	
	
	public ChatModel getChatById(long id) {
		String token = cookie.readCookie("");
		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
		ResponseEntity<ChatModel> response = fetch.exchange(url.append("/" + id).toString(),HttpMethod.GET ,entity,ChatModel.class);
		return response.getBody();
	}
	
	public void addIntegrator(ChatModel chat, UserModel user) {
		ResponseEntity<?> response = fetch.postForEntity(url.append("/addUserToChat/" + user.getId()
		+ "/" + chat.getId()).toString()
				, user, null);
	}
	
	public List<LetterModel> getLetterByChat(ChatModel chat) {
		String token = cookie.readCookie("");
		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
		ResponseEntity<Mapper> response = fetch.exchange(url.append("/byChats/" + chat.getId()).toString(),HttpMethod.GET,entity ,Mapper.class);
		return response.getBody().getListaMensaje();
	}

	public boolean createChat(ChatModel chatcito) {
		String token = cookie.readCookie("");
		HttpEntity<ChatModel> entity = new HttpEntity<>(chatcito, header);
		header.setBearerAuth(token);
		ResponseEntity<ChatModel> response = fetch.exchange(url.append("/createChat").toString(), HttpMethod.POST, entity, ChatModel.class);
		return response.getBody().equals("Chat Saved");
	}
	
	
	public void updateChat(ChatModel chatcito) {
		String token = cookie.readCookie("");
		HttpEntity<ChatModel> entity = new HttpEntity<>(chatcito,header);
		header.setBearerAuth(token);
		fetch.exchange(url.toString(),HttpMethod.PUT ,entity, String.class);
	}
	
	public void deleteChat(long id) {
		String token = cookie.readCookie("");
		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
		fetch.exchange(url.append("/" + id).toString(),HttpMethod.DELETE, entity, String.class);
	}

	
}
