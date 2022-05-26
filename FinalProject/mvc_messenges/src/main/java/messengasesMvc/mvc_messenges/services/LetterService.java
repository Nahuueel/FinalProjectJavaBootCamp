package messengasesMvc.mvc_messenges.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import messengasesMvc.mvc_messenges.Model.LetterModel;


@Service
public class LetterService {
    
	
	@Autowired
	private RestTemplate fetch;
	
	
	private StringBuilder url = new StringBuilder("http://localhost:8080/api/messages");
	
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
