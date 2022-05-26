package messengasesMvc.mvc_messenges.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import messengasesMvc.mvc_messenges.Model.ChatModel;

@Service
public class ChatService {

	@Autowired
	private RestTemplate fetch;
	
	private StringBuilder url = new StringBuilder("http://localhost:8080/api/chat");
	
	
	public ChatModel getChatById(long id) {
		ResponseEntity<ChatModel> response = fetch.getForEntity(url.append("/" + id).toString(), ChatModel.class);
		return response.getBody();
	}
	

	public boolean createChat(ChatModel chatcito) {
		ResponseEntity<ChatModel> response = fetch.postForEntity(url.append("/createChat").toString(), chatcito, ChatModel.class);
		return response.getBody().equals("Chat Saved");
	}
	
	
	public void updateChat(ChatModel chatcito) {
		fetch.put(url.toString(), chatcito, String.class);
	}
	
	public void deleteChat(long id) {
		fetch.delete(url.append("/" + id).toString());
	}
}
