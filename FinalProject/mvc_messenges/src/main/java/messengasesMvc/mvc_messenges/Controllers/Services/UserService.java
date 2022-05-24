package messengasesMvc.mvc_messenges.Controllers.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import messengasesMvc.mvc_messenges.Model.UserModel;

@Service
public class UserService {
    
    @Autowired
    private RestTemplate fetch;

    private StringBuilder url = new StringBuilder("http://localhost:8080/api/users");

    public UserModel getUserById(long id) {
        ResponseEntity<UserModel> response = fetch.getForEntity(url.append("/" + id).toString(), UserModel.class);
        return response.getBody();
    }

    public boolean createUser(UserModel user) {
        ResponseEntity<String> response = fetch.postForEntity(url.append("/register").toString(), user, String.class);
        return response.getBody().equals("User Saved");
    }

    public void updateUser(UserModel user) {
        fetch.put(url.toString(), user, String.class);
        
    }

    public void deleteUser(long id){
        fetch.delete(url.append("/" + id).toString());
    }
}
