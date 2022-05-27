package messengasesMvc.mvc_messenges.Controllers.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import messengasesMvc.mvc_messenges.Model.ChatModel;
import messengasesMvc.mvc_messenges.Model.UserModel;

@Service
public class ChatService {

	@Autowired
	private RestTemplate fetch;
	
	private StringBuilder url = new StringBuilder("http://localhost:8080/api/chats");
	
	
	public ChatModel getChatByUser(UserModel user) {
		ResponseEntity<ChatModel> response = fetch.getForEntity(url.append("/" + user.getId()).toString(), ChatModel.class);
		return response.getBody();
	}
	
	public List<ChatModel> getChatFromUser(UserModel user){
		ResponseEntity<Mapper> response = fetch.getForEntity(url.append("/usersFromChats/" + user.getId()).toString(), Mapper.class);
		return response.getBody().getListaChats();
	}
	
	
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
