package messengasesMvc.mvc_messenges.Controllers.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import messengasesMvc.mvc_messenges.Model.UserModel;

@Service
public class UserService {
    
    @Autowired
    private RestTemplate fetch;

    @Autowired
    private CookieService cookie;
    
    @Autowired
    private HttpHeaders header;
    
    private StringBuilder url = new StringBuilder("http://localhost:8080/api");

    
    public String login(UserModel user) {
    	ResponseEntity<String> response = fetch.postForEntity("/login", user, String.class);
    	cookie.createCoockie(response.getBody());
    	return cookie.toString();
    }
    
    public UserModel getUserById(long id) {
    	String token = cookie.readCookie("");
		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
        ResponseEntity<UserModel> response = fetch.exchange(url.append("/users/" + id).toString(),HttpMethod.GET ,entity,UserModel.class);
        return response.getBody();
    }

    public UserModel getUserByUsername(String user) {
    	String token = cookie.readCookie("");
		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
		ResponseEntity<UserModel> response = fetch.exchange(url.append("/users/" + user).toString(),HttpMethod.GET ,entity,UserModel.class);
		return response.getBody();
	}
    
    public boolean createUser(UserModel user) {
        ResponseEntity<String> response = fetch.postForEntity(url.append("/users/register").toString(), user, String.class);
        return response.getBody().equals("User Saved");
    }

    public void updateUser(UserModel user) {
    	String token = cookie.readCookie("");
		HttpEntity<UserModel> entity = new HttpEntity<>(user,header);
		header.setBearerAuth(token);
        fetch.exchange(url.toString(),HttpMethod.PUT ,entity, String.class);
        
    }

    public void deleteUser(long id){
        fetch.delete(url.append("/" + id).toString());
    }

	
}

