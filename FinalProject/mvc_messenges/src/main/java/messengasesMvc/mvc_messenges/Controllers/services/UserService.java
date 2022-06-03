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
    
//    private StringBuilder url = new StringBuilder("http://localhost:8080/api");

    
    public String login(UserModel user) {
        StringBuilder url = new StringBuilder("http://localhost:8080/api/login");
    	ResponseEntity<String> response = fetch.postForEntity(url.toString(), user, String.class);
        return (response.getBody()).toString(); 
    }
    
    public UserModel getUserById(long id,String token) {
        StringBuilder url = new StringBuilder("http://localhost:8080/api/users/").append(id);

        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer " + token);
		
        HttpEntity<String> entity = new HttpEntity<>(header);  
        ResponseEntity<UserModel> response = new RestTemplate().exchange(url.toString(),HttpMethod.GET ,entity, UserModel.class);
        return response.getBody();
    }

    public UserModel getUserByUsername(String username, String token) {
        StringBuilder url = new StringBuilder("http://localhost:8080/api/users/byUsername");

        HttpHeaders header = new HttpHeaders();
        header.setBearerAuth(token);
		
        HttpEntity<String> entity = new HttpEntity<>(username,header);

		ResponseEntity<UserModel> response = new RestTemplate().exchange(url.toString(),HttpMethod.POST, entity, UserModel.class);
		return response.getBody();
	} 
    
    public boolean createUser(UserModel user) {
        StringBuilder url = new StringBuilder("http://localhost:8080/api/users/register"); 
        ResponseEntity<String> response = fetch.postForEntity(url.toString(), user, String.class);
        return response.getBody().equals("User Saved");
    }

    public void updateUser(UserModel user, String token) {
        StringBuilder url = new StringBuilder("http://localhost:8080/api/users");

        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer " + token);

		HttpEntity<UserModel> entity = new HttpEntity<>(user,header);
        
        fetch.exchange(url.toString(),HttpMethod.PUT ,entity, String.class);
        
    }

    public void deleteUser(long id, String token){
        StringBuilder url = new StringBuilder("http://localhost:8080/api/users");
        fetch.delete(url.append("/" + id).toString());
    }

	
}

