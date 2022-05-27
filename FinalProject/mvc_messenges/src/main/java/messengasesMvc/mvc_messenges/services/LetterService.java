package messengasesMvc.mvc_messenges.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import messengasesMvc.mvc_messenges.Model.ChatModel;
import messengasesMvc.mvc_messenges.Model.LetterModel;
import messengasesMvc.mvc_messenges.Model.UserModel;

@Service
public class LetterService {
    
	
	@Autowired
	private RestTemplate fetch;
	
	
	private StringBuilder url = new StringBuilder("http://localhost:8080/api/messages");
	
	
	public List<LetterModel> getLetterByChat(ChatModel chats) {	
		ResponseEntity<Mapper> response = fetch.getForEntity(url.append("/byChat/" + chats.getId()).toString(), Mapper.class);
		return response.getBody().getListaMensaje();
	}
	
	public List<LetterModel> getLetterByUser(UserModel user) {
		ResponseEntity<Mapper> response = fetch.getForEntity(url.append("/byUser/" + user.getId()).toString(), Mapper.class);
		return response.getBody().getListaMensaje();
	}
	
	public LetterModel getLetterById(long id) {
		ResponseEntity<LetterModel> response = fetch.getForEntity(url.append("/" + id).toString(), LetterModel.class);
		return response.getBody();
	}
	
	
	public boolean createLetter (LetterModel mensajito) {
		ResponseEntity<String> response = fetch.postForEntity(url.append("/").toString(), mensajito, String.class);
		return response.getBody().equals("Message saved");
	}
	
	
	public void updateLetter (LetterModel letter) {
		fetch.put(url.toString(), letter, String.class);
	}
	
	
	public void deleteLetter(long id) {
		fetch.delete(url.append("/" + id).toString());
	}
}
