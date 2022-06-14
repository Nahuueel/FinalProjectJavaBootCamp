package messengasesMvc.mvc_messenges.Controllers.Services;


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
    private HttpHeaders header;
    
    
    public String login(UserModel user) throws Exception {
        StringBuilder url = new StringBuilder("http://localhost:8080/api/login");
    	try {
	        ResponseEntity<String> response = fetch.postForEntity(url.toString(), user, String.class);
	        return response.getBody().toString();
    	} catch(Exception e) {
    		throw new Exception("BAD_CREDENTIALS_EXCEPTION", e);
    	}
 
    }
    
    public UserModel getUserById(long id,String token) {
        StringBuilder url = new StringBuilder("http://localhost:8080/api/users/").append(id);

		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);

        ResponseEntity<UserModel> response = new RestTemplate().exchange(url.toString(),HttpMethod.GET ,entity, UserModel.class);
        return response.getBody();
    }

    public UserModel getUserByUsername(String username, String token) {
        StringBuilder url = new StringBuilder("http://localhost:8080/api/users/byUsername/").append(username);

		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
        
		ResponseEntity<UserModel> response = new RestTemplate().exchange(url.toString(),HttpMethod.GET, entity, UserModel.class);
		return response.getBody();
	} 
    
    public boolean createUser(UserModel user) {
        StringBuilder url = new StringBuilder("http://localhost:8080/api/users/register"); 
        try {
        	ResponseEntity<String> response = fetch.postForEntity(url.toString(), user, String.class);        	
        	return response.getBody().equals("User Saved");
        } catch(Exception e) {
        	return false;
        }
    }

    public void updateUser(UserModel user, String token) {
        StringBuilder url = new StringBuilder("http://localhost:8080/api/users");

		HttpEntity<String> entity = new HttpEntity<>(header);
		header.setBearerAuth(token);
        
        ResponseEntity<String> response = new RestTemplate().exchange(url.toString(),HttpMethod.PUT ,entity, String.class);
        
    }

    public void deleteUser(long id, String token){
        StringBuilder url = new StringBuilder("http://localhost:8080/api/users");
        fetch.delete(url.append("/" + id).toString());
    }

	
}

