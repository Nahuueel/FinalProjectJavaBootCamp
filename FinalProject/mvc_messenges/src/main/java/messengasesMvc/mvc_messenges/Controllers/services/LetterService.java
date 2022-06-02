<<<<<<< HEAD
package messengasesMvc.mvc_messenges.Controllers.Services;
=======
package messengasesMvc.mvc_messenges.Controllers.services;
>>>>>>> Ariel's-Branch


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
public class LetterService {
    
	
	@Autowired
	private RestTemplate fetch;
	
	@Autowired
	private CookieService cookie;
	
	@Autowired
	private HttpHeaders header;
	
	private StringBuilder url = new StringBuilder("http://localhost:8080/api/messages");
	
	
<<<<<<< HEAD
	public List<LetterModel> getLetterByChat(ChatModel chats, String token) {	
//		String token = cookie.readCookie("");
=======
	public List<LetterModel> getLetterByChat(ChatModel chats) {	
		String token = cookie.readCookie("");
>>>>>>> Ariel's-Branch
		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
		ResponseEntity<Mapper> response = fetch.exchange(url.append("/byChat/" + chats.getId()).toString(), HttpMethod.GET ,entity,Mapper.class);
		return response.getBody().getListaMensaje();
	}
	
<<<<<<< HEAD
	public List<LetterModel> getLetterByUser(UserModel user, String token) {
//		String token = cookie.readCookie("");
=======
	public List<LetterModel> getLetterByUser(UserModel user) {
		String token = cookie.readCookie("");
>>>>>>> Ariel's-Branch
		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
		ResponseEntity<Mapper> response = fetch.exchange(url.append("/byUser/" + user.getId()).toString(), HttpMethod.GET ,entity,Mapper.class);
		return response.getBody().getListaMensaje();
	}
	
<<<<<<< HEAD
	public LetterModel getLetterById(long id, String token) {
//		String token = cookie.readCookie("");
=======
	public LetterModel getLetterById(long id) {
		String token = cookie.readCookie("");
>>>>>>> Ariel's-Branch
		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
		ResponseEntity<LetterModel> response = fetch.exchange(url.append("/" + id).toString(), HttpMethod.GET ,entity,LetterModel.class);
		return response.getBody();
	}
	
	
<<<<<<< HEAD
	public boolean createLetter (LetterModel mensajito, String token) {
//		String token = cookie.readCookie("");
=======
	public boolean createLetter (LetterModel mensajito) {
		String token = cookie.readCookie("");
>>>>>>> Ariel's-Branch
		HttpEntity<LetterModel> entity = new HttpEntity<>(mensajito,header);
		header.setBearerAuth(token);
		ResponseEntity<String> response = fetch.exchange(url.append("/").toString(), HttpMethod.POST ,entity, String.class);
		return response.getBody().equals("Message saved");
	}
	
	
<<<<<<< HEAD
	public void updateLetter (LetterModel letter, String token) {
//		String token = cookie.readCookie("");
=======
	public void updateLetter (LetterModel letter) {
		String token = cookie.readCookie("");
>>>>>>> Ariel's-Branch
		HttpEntity<LetterModel> entity = new HttpEntity<>(letter,header);
		header.setBearerAuth(token);
		fetch.exchange(url.toString(), HttpMethod.PUT ,entity, String.class);
	}
	
	
<<<<<<< HEAD
	public void deleteLetter(long id, String token) {
//		String token = cookie.readCookie("");
=======
	public void deleteLetter(long id) {
		String token = cookie.readCookie("");
>>>>>>> Ariel's-Branch
		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
		fetch.exchange(url.append("/" + id).toString(),HttpMethod.DELETE ,entity,String.class);
	}
}